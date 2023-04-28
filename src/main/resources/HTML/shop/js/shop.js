const checkReady = () => {
  return document.readyState
}

const HUtils = function () {
  const buttons = function() {
    const buttons = [].slice.call(document.querySelectorAll('.btn'))
    buttons.map((button) => {
    })
  }

  return {
    init: function () {
      console.log('init')
    },

    getURLParam: function(paramName) {
      var searchString = window.location.search.substring(1),
        i, val, params = searchString.split("&");

      for (i = 0; i < params.length; i++) {
        val = params[i].split("=");
        if (val[0] === paramName) {
          return unescape(val[1]);
        }
      }

      return null;
    },


    getViewPort: function() {
      let e = window,
        a = 'inner';
      if (!('innerWidth' in window)) {
        a = 'client';
        e = document.documentElement || document.body;
      }

      return {
        width: e[a + 'Width'],
        height: e[a + 'Height']
      };
    },

    show: function(el, display) {
      if (typeof el !== 'undefined') {
        el.style.display = (display ? display : 'block');
      }
    },

    hide: function(el) {
      if (typeof el !== 'undefined') {
        el.style.display = 'none';
      }
    },

    addEvent: function(el, type, handler, one) {
      if (typeof el !== 'undefined' && el !== null) {
        el.addEventListener(type, handler);
      }
    },

    removeEvent: function(el, type, handler) {
      if (el !== null) {
        el.removeEventListener(type, handler);
      }
    },

    setHTML: function(el, html) {
      el.innerHTML = html
    },

    getHTML: function(el) {
      if(el) {
        return el.innerHTML
      }
    },

    throttle: function(timer, func, delay) {
      if(timer) {
        return
      }

      timer = setTimeout(function() {
        func()

        timer = undefined
      }, delay)
    },

    debounce: function (timer, func, delay) {
      clearTimeout(timer)

      timer  =  setTimeout(func, delay);
    },



    buttons: function() {
      buttons()
    }
  }
}()

Element.prototype

console.log(Array.prototype.slice)


window.HUtils  = HUtils;

