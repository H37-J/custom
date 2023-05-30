const changePage = (page) => {
  const startPage = ((page / 10) * 10) + 1
  const endPage = startPage + 9
}


const getCurrentDate = () => {
  const current = new Date()
  return current.toISOString().split('T')[0].replaceAll('-', '').substring(0, 6);
}

getCurrentDate()
