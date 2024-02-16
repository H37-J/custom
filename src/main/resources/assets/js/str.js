const charRegex = (val) => {
    const regex = /^[가-힣a-zA-Z]+$/;
    return regex.test(val);
}
console.log(charRegex('Hyein'), charRegex('Shin'))