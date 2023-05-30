const data = {
  name: 'hjk',
  age: 30
}

const has = Object.prototype.hasOwnProperty
const res = has.call(data, 'name');



Object.entries(data).map(([key,value]) => {
  console.log(key)
  console.log(value)
});
