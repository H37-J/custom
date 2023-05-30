

const constChange = (_this) => {
  _this.value = _this.value.replace(/\D/g, '')
  const next_pmt_cycle = document.querySelector('#next_pmt_cycle')
  if (_this.value >= 190000) {
    next_pmt_cycle.textContent = '연납'
  } else {
    next_pmt_cycle.textContent = '월납'
  }
}


const getContract = (APT_CODE) => {
  const config = {
    method: 'post',
    maxBodyLength: Infinity,
    url: `http://localhost:8081/CustomContractAction.do`,
    headers: {
      'Cookie': 'JSESSIONID=92DCEA4B318D357F5F2EBA31792D078D',
      "Content-Type": 'application/x-www-form-urlencoded'
    },
    data: {
      command: 'selectContract',
      APT_CODE: APT_CODE
    }
  }
  axios(config).then((res) => {
    const data = res.data.datas[0]
    document.querySelector('#apt_code').textContent = data.단지명
    document.querySelector('#contract_date').textContent = data.계약기간
    document.querySelector('#cost').textContent = data.금액
    document.querySelector('#pmt_cycle').textContent = data.납부주기
    document.querySelector('#pmt_way').textContent = data.납부방법
    document.querySelector('#pmt_date').textContent = data.수수료청구일

    document.querySelector('#next_apt_code').textContent = data.단지명
    document.querySelector('#next_pmt_way').textContent = data.납부방법
    document.querySelector('#next_pmt_date').textContent = data.수수료청구일


    document.querySelector('#code').value = data.apt_code
  })
}

const getList = (page) => {

  const list = document.querySelector('#list');
  list.innerHTML = ''

  const config = {
    method: 'post',
    maxBodyLength: Infinity,
    url: `http://localhost:8081/CustomContractAction.do`,
    headers: {
      'Cookie': 'JSESSIONID=92DCEA4B318D357F5F2EBA31792D078D',
      "Content-Type": 'application/x-www-form-urlencoded'
    },
    data: {
      command: 'selContractList',
      page: page
    }
  }
  axios(config).then((res) => {
    const datas = res.data.datas

    console.log(datas)

    datas.map((data) => {
      const tr = document.createElement('tr')
      const td1 = document.createElement('td')
      const td2 = document.createElement('td')
      const td3 = document.createElement('td')
      const td4 = document.createElement('td')
      const td5 = document.createElement('td')
      const td6 = document.createElement('td')
      const td7 = document.createElement('td')
      const td8 = document.createElement('td')
      const td9 = document.createElement('td')
      const td10 = document.createElement('td')
      const td11 = document.createElement('td')
      const td12 = document.createElement('td')
      const td13 = document.createElement('td')
      const td14 = document.createElement('td')
      const td15 = document.createElement('td')
      const td16 = document.createElement('td')
      const td17 = document.createElement('td')
      const td18 = document.createElement('td')
      const td19 = document.createElement('td')
      const td20 = document.createElement('td')
      const td21 = document.createElement('td')
      const td22 = document.createElement('td')
      const td23 = document.createElement('td')
      const td24 = document.createElement('td')
      const td25 = document.createElement('td')
      const td26 = document.createElement('td')
      const td27 = document.createElement('td')
      const td28 = document.createElement('td')
      const td29 = document.createElement('td')

      td1.textContent = data.코드
      td2.textContent = data.단지명
      td3.textContent = data.가입일
      td4.textContent = data.계약갱신
      td5.textContent = data.현재청구일
      td6.textContent = data.현재계약시작일
      td7.textContent = data.현재계약종료일
      td8.textContent = data.현재계약주기
      td9.textContent = data.현재금액
      td10.textContent = data.현재납부주기
      td11.textContent = data.현재납부방법
      td12.textContent = data.현재세무대리
      td13.textContent = data.현재폼

      td14.textContent = data.다음청구일
      td15.textContent = data.다음계약시작일
      td16.textContent = data.다음계약종료일
      td17.textContent = data.다음계약주기
      td18.textContent = data.다음금액
      td19.textContent = data.다음납부주기
      td20.textContent = data.다음납부방법
      td21.textContent = data.다음세무대리
      td22.textContent = data.다음폼
      td23.textContent = data.계약방법
      td24.textContent = data.재계약단계
      td25.textContent = data.승인
      td26.textContent = data.이슈
      td27.textContent = data.합산코드
      td28.textContent = data.계약서
      td29.textContent = data.로그

      tr.appendChild(td1)
      tr.appendChild(td2)
      tr.appendChild(td3)
      tr.appendChild(td4)
      tr.appendChild(td5)
      tr.appendChild(td6)
      tr.appendChild(td7)
      tr.appendChild(td8)
      tr.appendChild(td9)
      tr.appendChild(td10)
      tr.appendChild(td11)
      tr.appendChild(td12)
      tr.appendChild(td13)
      tr.appendChild(td14)
      tr.appendChild(td15)
      tr.appendChild(td16)
      tr.appendChild(td17)
      tr.appendChild(td18)
      tr.appendChild(td19)
      tr.appendChild(td20)
      tr.appendChild(td21)
      tr.appendChild(td22)
      tr.appendChild(td23)
      tr.appendChild(td24)
      tr.appendChild(td25)
      tr.appendChild(td26)
      tr.appendChild(td27)
      tr.appendChild(td28)
      tr.appendChild(td29)

      list.appendChild(tr)
    })
  })
}


const createContract = () => {

  const apt_code = document.querySelector('#code').value;
  const cont_dt_from = document.querySelector('#next_cont_from').value;
  const cont_dt_to = document.querySelector('#next_cont_to').value;
  const mo_re_amt = document.querySelector('#next_cost').value;
  const mo_re_amt_ann = mo_re_amt * 11

  const config = {
    method: 'post',
    maxBodyLength: Infinity,
    url: `https://apttax.co.kr/CustomContractAction.do`,
    headers: {
      'Cookie': 'JSESSIONID=92DCEA4B318D357F5F2EBA31792D078D',
      "Content-Type": 'application/x-www-form-urlencoded'
    },
    data: {
      command: 'createContract',
      apt_code,
      cont_dt_from,
      cont_dt_to,
      apt_sort: '1',
      mo_re_amt,
      mo_re_amt_ann,
      form_type: 'default',
      sub_form_type: '',
      guide_include_chk: 'Y',
      cont_txt_include: 'Y',
      YN_BIZ_MGR: 'N',
      sort: 're_cont',
    }
  }


  axios(config).then((res) => {


    const loadingEl = document.createElement("div");
    document.body.prepend(loadingEl);
    loadingEl.classList.add("page-loader");
    loadingEl.classList.add("flex-column");
    loadingEl.classList.add("bg-dark");
    loadingEl.classList.add("bg-opacity-25");
    loadingEl.innerHTML = `
        <span class="spinner-border text-primary" role="status"></span>
        <span class="text-gray-800 fs-6 fw-semibold mt-5">PDF생성중...</span>
    `

    showPageLoading();

    setTimeout(function() {
      hidePageLoading();
      loadingEl.remove();
      location.href = `http://localhost:63342/hjk/src/main/resources/HTML/apttax/pdf.html?apt_code${apt_code}`

    }, 500);
  })
}

const viewPdf = () => {
  const apt_code = '95001'

  const config = {
    method: 'post',
    maxBodyLength: Infinity,
    url: `https://apttax.co.kr/CustomContractAction.do`,
    headers: {
      'Cookie': 'JSESSIONID=92DCEA4B318D357F5F2EBA31792D078D',
      "Content-Type": 'application/x-www-form-urlencoded'
    },
    data: {
      command: 'viewPdf',
      apt_code
    }
  }

  axios(config).then((res) => {
    const data = res.data.data
    createPdf(data.path)
  })


  const createPdf = (path) => {
    const url = `http://localhost:8081${path}`

    const pdfjsLib = window['pdfjs-dist/build/pdf']
    pdfjsLib.GlobalWorkerOptions.workerSrc = '//mozilla.github.io/pdf.js/build/pdf.worker.js'

    const loadingTask = pdfjsLib.getDocument(url)
    loadingTask.promise.then(function (pdf) {

      const pageNumber = 1;
      pdf.getPage(pageNumber).then(function (page) {

        const scale = 1.5;
        const viewport = page.getViewport({scale: scale})

        const canvas = document.querySelector('#pdf')
        const context = canvas.getContext('2d')

        canvas.height = 1300;
        canvas.width = 1200;

        const renderContext = {
          canvasContext: context,
          viewport: viewport
        }

        const renderTask = page.render(renderContext);
        renderTask.promise.then(function () {
        })
      })
    }, function (reason) {
      console.error(reason);
    })
  }
}

const saveContract = (APT_CODE, path) => {
  const apt_code = APT_CODE
  const config = {
    method: 'post',
    maxBodyLength: Infinity,
    url: `http://localhost:8081/CustomContractAction.do`,
    headers: {
      'Cookie': 'JSESSIONID=92DCEA4B318D357F5F2EBA31792D078D',
      "Content-Type": 'application/x-www-form-urlencoded'
    },
    data: {
      command: 'saveContract',
      apt_code,
      path
    }
  }


  axios(config).then((res) => {
    console.log(res.data)
  })
}


const showPageLoading = function() {
  document.body.classList.add('page-loading');
  document.body.setAttribute('page-loading', "on");
}

const hidePageLoading = function() {
  document.body.classList.remove('page-loading');
  document.body.removeAttribute('page-loading');
}

