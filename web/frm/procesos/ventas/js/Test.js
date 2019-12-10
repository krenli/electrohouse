import { numeroLetras } from './numeroLetras.js';

const addCommas = (value = 0, fixed = 0) => value.toFixed(fixed).replace(/./g, (c, i, a) => {
  return i && c !== '.' && ((a.length - i) % 3) === 0 ? ',' + c : c;
});

const testing = [
  7,
  17,
  997,
  1007,
  2017,
  2917,
  37093,
  527465,
  1001017,
  9117942
];

testing.map(cantidad => {
  console.log('-----------------------');
  console.log(addCommas(cantidad));
  console.log(NumberAsString(cantidad));
});