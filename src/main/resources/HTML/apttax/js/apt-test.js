import axios from 'axios'

const getTest = () => {
  const command = 'insertIp'
  const ip_address = '130.15.243.164'
  const url = '3'
  const config = {
    method: 'post',
    maxBodyLength: Infinity,
    url: `http://localhost:8081/PageAction.do`,
    headers: {
      "Content-Type": 'application/x-www-form-urlencoded'
    },
    data: {
      command,
      ip_address,
      url
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
//     url: `https://apttax.co.kr/CustomContractAction.do`,
//     headers: {
//       'Cookie': 'JSESSIONID=92DCEA4B318D357F5F2EBA31792D078D',
//       "Content-Type": 'application/x-www-form-urlencoded'
//     },
//     data: {
//       command: 'getContractFile',
//       apt_code: '95001'
//     }
//   }
//
//
//   axios(config).then((res) => {
//     console.log(res.data)
//   })
// }
// getTest()
//
// const saveTest = (APT_CODE) => {
//   const config = {
//     method: 'post',
//     maxBodyLength: Infinity,
//     url: `https://apttax.co.kr/CustomContractAction.do`,
//     headers: {
//       'Cookie': 'JSESSIONID=92DCEA4B318D357F5F2EBA31792D078D',
//       "Content-Type": 'application/x-www-form-urlencoded'
//     },
//     data: {
//       command: 'saveContract',
//       path:'http://121.189.24.187/kakao/img/kakao_1.png',
//       apt_code: '95001'
//     }
//   }
//
//
//   axios(config).then((res) => {
//     console.log(res.data)
//   })
// }
// saveTest()


// const test = (APT_CODE) => {
//   const config = {
//     method: 'post',
//     maxBodyLength: Infinity,
//     url: `https://apttax.co.kr/CustomContractAction.do`,
//     headers: {
//       'Cookie': 'JSESSIONID=92DCEA4B318D357F5F2EBA31792D078D',
//       "Content-Type": 'application/x-www-form-urlencoded'
//     },
//     data: {
//       command: 'createContract',
//       apt_code: '95001',
//       cont_dt_from: '20230401',
//       cont_dt_to: '20231010',
//       apt_sort: '1',
//       mo_re_amt: '50000',
//       mo_re_amt_ann: '550000',
//       form_type: 'default',
//       sub_form_type: '',
//       guide_include_chk: 'Y',
//       cont_txt_include: 'Y',
//       YN_BIZ_MGR: 'N',
//       sort: 're_cont',
//     }
//   }
//
//   axios(config).then((res) => {
//     console.log(res.data)
//   })
// }
// test()

//
//
//   axios(config).then((res) => {
//     console.log(res.data)
//   })
// }
// test()

//
// //
// const test = (APT_CODE) => {
//   const config = {
//     method: 'post',
//     maxBodyLength: Infinity,
//     url: `http://localhost:8081/CustomTaxAction.do`,
//     headers: {
//       'Cookie': 'JSESSIONID=92DCEA4B318D357F5F2EBA31792D078D',
//       "Content-Type": 'application/x-www-form-urlencoded'
//     },
//     data: {
//       command: 'kakao_one',
//       APT_CODE: '95001',
//       name: 't',
//       date: '2023-03-01',
//       email: 'these9907@kwic.co.kr',
//       price: '10000',
//     }
//   }
//   axios(config).then((res) => {
//     console.log(res.data)
//   })
// }
// test()
//
//
//
//
// const getContract = (APT_CODE) => {
//   const config = {
//     method: 'post',
//     maxBodyLength: Infinity,
//     url: `http://localhost:8081/CustomContractAction.do`,
//     headers: {
//       'Cookie': 'JSESSIONID=92DCEA4B318D357F5F2EBA31792D078D',
//       "Content-Type": 'application/x-www-form-urlencoded'
//     },
//     data: {
//       command: 'selectContract',
//       APT_CODE: APT_CODE
//     }
//   }
//   axios(config).then((res) => {
//     const data = res.data.datas[0]
//     console.log(data)
//
//   })
// }
//
// getContract('95001')

