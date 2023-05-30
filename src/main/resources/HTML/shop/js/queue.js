class Queue {
  constructor(contents = []) {
    this.queue = [...contents]
  }

  push(val) {
    this.queue.push(val)
  }

  pop() {
    return this.queue.splice(0, 1)
  }
}


const queue = new Queue()
queue.push(3)

const res = queue.pop()

// console.log(res)
// console.log(queue)


const numbers = [1, 2, 3, 4]

const arr = numbers.map((num) => num + 1)
// console.log(arr)

const sum = numbers.reduce((total, num) => total + num, 0)
// console.log(sum)
