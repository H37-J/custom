const factorial = (number) => {
  let val = 1;

  for(let i = 2; i <= number; i+= 1) {
    val *= number
  }
  return val
}

