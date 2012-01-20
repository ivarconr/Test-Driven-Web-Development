var slides;
var startTime;
var limits = [];

$(function() {
  $('div.slide .content').each(function(i, slide) {
    limits.push(5);
  });
});

var startClocks = function() {
  startTime = new Date();
  slides = $('div.slide .content');

  var updateClocks = function() {
    var millisecondsUsed = new Date().getTime() - startTime.getTime();
    var minutesUsed = Math.floor(millisecondsUsed / 1000 / 60);
    
    var availableTime;
    $.each(slides, function(i, slide) {
      slide = $(slide)
      var clock = slide.find('div.clock');
      var slideLimit = limits[i];
      availableTime += slideLimit;
      clock.html('Brukt: ' + minutesUsed);
      clock.append('Du er ' + (availableTime - minutesUsed) + " foran skjema");
    });
  };

  var appendClocks = function() {
    $.each(slides, function(i, slide) {
      var clock = $('<div>').addClass('clock').css('border', '1px solid black').css('width', '100px').css('height', '100px');
      $(slide).append(clock);
    });
  };

  appendClocks();

  setInterval(updateClocks, 1000);

}

//setTimeout(startClocks, 1000);
