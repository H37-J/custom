
import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  vus: 10,
  duration: '30s',
  thresholds: {
    http_req_failed: ['rate < 0.01'], // http errors should be less than 1%
    http_req_duration: ['p(95) < 500'], // 95% of requests should be below 200ms
  },
};

export default function () {
  const url = 'http://localhost:8080/test/hjk';
  const params = {
    headers: {},
  };
  const payload = null;

  const res = http.get(url, params);

  check(res, {
    'status is 200': (r) => r.status == 200,
  });

  sleep(1);
}
