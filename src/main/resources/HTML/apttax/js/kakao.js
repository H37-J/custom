
const currentPage = document.querySelector('#currentPage')
const prevButton = document.querySelector('#prevButton')
const nextButton = document.querySelector('#nextButton')
const page_container = document.querySelector('#page-container')
const month = document.querySelector('#month')

let startPage = 0
let endPage = 0
let totalPage = 0
let selectedDate = new Date().toISOString().split('T')[0].replaceAll('-', '').substring(0, 6);

axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
axios.interceptors.response.use(function (res) {
  return res.data
});

const init = async (date) => {
  selectDate()
  await setInitPage(date)
  await getList(date)
}

const getList = async (date = selectedDate, page = 1) => {
  selectedDate = date

  const list = document.querySelector('#list');
  list.innerHTML = ''


  const config = {
    method: 'post',
    url: `http://localhost:8081/CustomKakaoAction.do`,
    data: {
      command: 'selectList',
      date,
      page
    }
  }

  axios(config).then((res) => {
    const datas = res.datas

    datas.map((data) => {
      const tr = document.createElement('tr')
      const td1 = document.createElement('td')
      const td2 = document.createElement('td')
      const td3 = document.createElement('td')
      const td4 = document.createElement('td')
      const td5 = document.createElement('td')
      const td6 = document.createElement('td')
      const td7 = document.createElement('td')

      const button = document.createElement('button')
      button.type = 'button'
      button.textContent = '발송'
      button.classList = 'btn btn-primary'
      button.setAttribute('apt_code', data.단지코드)
      button.setAttribute('tel', data.manager_tel_no1 + data.manager_tel_no2 + data.manager_tel_no3)
      button.setAttribute('name', data.cust_name)
      button.setAttribute('email', data.manager_email)
      button.addEventListener('click', () => {
        sendKakao(button)
      })


      td1.textContent = data.단지코드
      td2.textContent = data.cust_name
      td3.textContent = data.manager_email
      td4.textContent = data.manager_tel_no1 + data.manager_tel_no2 + data.manager_tel_no3
      td5.textContent = data.cust_register_no
      td6.textContent = data.bigo
      td7.appendChild(button)


      tr.appendChild(td1)
      tr.appendChild(td2)
      tr.appendChild(td3)
      tr.appendChild(td4)
      tr.appendChild(td5)
      tr.appendChild(td6)
      tr.appendChild(td7)

      list.appendChild(tr)
    })
    changePage()
  }, (err) => {
    console.log('test')
  })
}

const setInitPage = async (date = getCurrentDate(), page = 1) => {
  const config = {
    method: 'post',
    url: `http://localhost:8081/CustomKakaoAction.do`,
    data: {
      command: 'totalCount',
      date,
      page
    }
  }

  await axios(config).then((res) => {
    const num = res.data
    document.querySelector('#totalCount').textContent = '(' + num + '건)'
    startPage = 1
    endPage = Math.floor(((num % 10) === 0 ? (num / 10) > 10 ? 10 : (num % 10) === 0 ? (num / 10) : (num / 10) + 1 : (num / 10) + 1 > 10 ? 10 : (num % 10) === 0 ? (num / 10) : (num / 10) + 1))
    totalPage = Math.floor(num % 10 === 0 ? (num / 10) : (num / 10) + 1)
  })
}

const selectDate = () => {
  const id = '#date_' + selectedDate
  document.querySelector(id).selected = true
  month.textContent = document.querySelector(id).getAttribute('month')
}

const changeDate = (_this) => {
  const option = _this.options[_this.selectedIndex]
  month.textContent = option.getAttribute('month')
  selectedDate = _this.options[_this.selectedIndex].value
  init(selectedDate)
}

const changePage = () => {
  page_container.innerHTML = ''
  endPage = endPage > totalPage ? totalPage : endPage
  if (startPage > 10) {
    prevButton.classList.remove('disabled')
  } else {
    prevButton.classList.add('disabled')
  }
  if (endPage < 10 || endPage === totalPage) {
    nextButton.classList.add('disabled')
  } else {
    nextButton.classList.remove('disabled')
  }
  for (let i = startPage; i <= endPage; i++) {
    renderPage(i)
  }
}

const renderPage = (i) => {
  const li = document.createElement('li')
  li.classList.add('page-item')
  li.addEventListener('click', () => {
    getList(selectedDate, i)
  })

  const a = document.createElement('a')
  a.classList.add('page-link')
  a.textContent = i
  a.href = '#'

  li.appendChild(a)
  page_container.appendChild(li)
}

const prevPage = (_this) => {
  if (disableCheck(_this)) {
    return
  }

  startPage = parseInt(startPage) - 10
  endPage = parseInt(startPage) + 9

  getList(selectedDate, startPage)
}

const nextPage = (_this) => {
  if (disableCheck(_this)) {
    return
  }

  startPage = parseInt(endPage) + 1
  endPage = parseInt(startPage) + 9

  getList(selectedDate, startPage)
}


const disableCheck = (_this) => {
  return _this.classList.contains('disabled')
}

const getCurrentDate = () => {
  const current = new Date()
  return current.toISOString().split('T')[0].replaceAll('-', '').substring(0, 6);
}

const goLast = async (date = selectedDate, page) => {
  startPage = 191
  endPage = 200
  await getList(date, page)
}


const sendKakao = (_this, date = selectedDate) => {
  const msg = '알림을 전송 하시겠습니까?'
  if(!confirm(msg)) {
    return
  }

  const apt_code = _this.getAttribute('apt_code')
  const tel = _this.getAttribute('tel')
  const email = _this.getAttribute('email')
  const name = _this.getAttribute('name')

  const config = {
    method: 'post',
    maxBodyLength: Infinity,
    url: `http://localhost:8081/CustomKakaoAction.do`,
    headers: {
      "Content-Type": 'application/x-www-form-urlencoded'
    },
    data: {
      command: 'send',
      apt_code,
      name,
      tel,
      email,
      date
    }
  }


  axios(config).then((res) => {
    if(res.result === 1) {
      alert('알림을 전송 하였습니다')
    }
  })
}

const sendKakaoAll = (_this, date = selectedDate) => {
  const msg = '알림을 전송 하시겠습니까?'
  if(!confirm(msg)) {
    return
  }


  const config = {
    method: 'post',
    maxBodyLength: Infinity,
    url: `http://localhost:8081/CustomKakaoAction.do`,
    headers: {
      "Content-Type": 'application/x-www-form-urlencoded'
    },
    data: {
      command: 'sendAll',
      date
    }
  }


  axios(config).then((res) => {
    if(res.result > 0) {
      const msg = res.result + '건의 알림을 전송 하였습니다'
      alert(msg)
    }
  })
}
