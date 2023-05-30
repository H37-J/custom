import axios from 'axios'

const getTest = (APT_CODE) => {
  const apt_code = '95001'
  const tel = '01040186763'
  const email = 'these9907@kwic.co.kr'
  const name = "기웅아파트"
  const config = {
    method: 'post',
    maxBodyLength: Infinity,
    url: `http://localhost:8081/CustomKakaoAction.do`,
    headers: {
      "Content-Type": 'application/x-www-form-urlencoded'
    },
    data: {
      date: '202306',
      command: 'send',
      apt_code,
      name,
      tel,
      email
    }
  }


  axios(config).then((res) => {
    console.log(res.data)
  })
}
getTest()

// const getTest = (APT_CODE) => {
//   const config = {
//     method: 'post',
//     maxBodyLength: Infinity,
//     url: `http://localhost:8081/CustomKakaoAction.do`,
//     headers: {
//       'Cookie': 'JSESSIONID=92DCEA4B318D357F5F2EBA31792D078D',
//       "Content-Type": 'application/x-www-form-urlencoded'
//     },
//     data: {
//       command: 'selectList',
//       date: '202305',
//     }
//   }
//
//
//   axios(config).then((res) => {
//     console.log(res.data)
//   })
// }
// getTest()

// const getTest = (APT_CODE) => {
//   const config = {
//     method: 'post',
//     maxBodyLength: Infinity,
//     url: `http://localhost:8081/ActManager2.do`,
//     headers: {
//       'Cookie': 'JSESSIONID=15B8095669958934589B0E4D0B2663F9',
//       "Content-Type": 'application/x-www-form-urlencoded'
//     },
//     data: {
//       command: 'mngBbs0300LP',
//     }
//   }
//   axios(config).then((res) => {
//     console.log(res.data)
//   })
// }
// getTest()
