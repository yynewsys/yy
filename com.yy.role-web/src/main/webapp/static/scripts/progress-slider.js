'use strict';

var demoProgressSliders = function () {

  function events() {
    $(document).on('click', '#updateLast', function (e) {

      e.preventDefault();

      $('#example_8').ionRangeSlider('update', {
        min: Math.round(10000 + Math.random() * 40000),
        max: Math.round(200000 + Math.random() * 100000),
        step: 1,
        from: Math.round(40000 + Math.random() * 40000),
        to: Math.round(150000 + Math.random() * 80000)
      });

    });
  }

  function plugins() {
    $('.sliders input').slider();
    $('#eg input').slider();
    $('#sl2').slider();

    $('#example_1').ionRangeSlider({
      min: 0,
      max: 5000,
      type: 'double',
      prefix: '$',
      maxPostfix: '+',
      prettify: false,
      hasGrid: true
    });

    $('#example_2').ionRangeSlider({
      min: 1000,
      max: 100000,
      from: 30000,
      to: 90000,
      type: 'double',
      step: 500,
      postfix: ' &euro;',
      hasGrid: true
    });

    $('#example_3').ionRangeSlider({
      min: 0,
      max: 10,
      type: 'single',
      step: 0.1,
      postfix: ' carats',
      prettify: false,
      hasGrid: true
    });

    $('#example_4').ionRangeSlider({
      min: -50,
      max: 50,
      from: 0,
      type: 'single',
      step: 1,
      postfix: '°',
      prettify: false,
      hasGrid: true
    });

    $('#example_5').ionRangeSlider({
      values: [
                'January', 'February', 'March',
                'April', 'May', 'June',
                'July', 'August', 'September',
                'October', 'November', 'December'
            ],
      type: 'single',
      hasGrid: true
    });

    $('#example_6').ionRangeSlider({
      min: 10000,
      max: 100000,
      step: 100,
      postfix: ' light years',
      from: 55000,
      hideMinMax: false,
      hideFromTo: true
    });

    $('#example_7').ionRangeSlider({
      min: 10000,
      max: 100000,
      step: 100,
      postfix: ' light years',
      from: 55000,
      hideMinMax: true,
      hideFromTo: false
    });

    $('#example_8').ionRangeSlider({
      min: 1000000,
      max: 100000000,
      type: 'double',
      postfix: ' р.',
      step: 10000,
      from: 25000000,
      to: 35000000,
      onChange: function (obj) {
        delete obj.input;
        delete obj.slider;
        var t = 'Range Slider value: ' + JSON.stringify(obj, '', 2);

        $('#result').html(t);
      },
      onLoad: function (obj) {
        delete obj.input;
        delete obj.slider;
        var t = 'Range Slider value: ' + JSON.stringify(obj, '', 2);

        $('#result').html(t);
      }
    });
  }

  return {
    init: function () {
      events();
      plugins();
    }
  };
}();

$(function () {
  demoProgressSliders.init();
});
