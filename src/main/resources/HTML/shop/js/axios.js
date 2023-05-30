import axios from "axios";
import fs from 'fs'

// axios({
//   method: 'get',
//   url: 'http://bit.ly/2mTM3nY',
//   responseType: 'stream'
// })
//   .then(function (response) {
//     response.data.pipe(fs.createWriteStream('ada_lovelace.jpg'))
//   });

// axios({
//   method:'get',
//   url: 'https://apttax.co.kr/kakao/img/kakao_1.png',
//   responseType: 'stream'
// }).then((res) => {
//   res.data.pipe(fs.createWriteStream(('kakao_1.png')))
// })


axios.interceptors.response.use((res) => {

  return res.data
})

axios.get('https://aragonk.com:41369')
.then((res) => console.log(res))
