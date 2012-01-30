(function() {
  var Clock, obj;
  var __bind = function(fn, me){ return function(){ return fn.apply(me, arguments); }; };

  Clock = (function() {

    function Clock() {
      this.appendClockToCurrentSlide = __bind(this.appendClockToCurrentSlide, this);
      this.updateClock = __bind(this.updateClock, this);      this.limits = [3, 15, 10, 3, 3, 3, 3, 3, 1, 5, 5, 5, 35, 8, 7, 4, 5, 5, 15, 10, 10, 10, 10, 10, 10, 10, 10, 10];
      this.startTime = new Date();
    }

    Clock.prototype.updateClock = function() {
      var availableTime, millisecondsUsed, minutesUsed;
      var _this = this;
      millisecondsUsed = new Date().getTime() - this.startTime.getTime();
      minutesUsed = Math.floor(millisecondsUsed / 1000 / 60);
      availableTime = 0;
      return $.each(this.slides, function(i, slide) {
        var clock, slideLimit;
        if (_this.isCurrentSlide(slide)) {
          slide = $(slide);
          clock = slide.find('div.clock');
          if (!(clock.length > 0)) clock = _this.appendClockToCurrentSlide();
          clock.html("" + minutesUsed + "/" + availableTime);
          _this.updateCss(clock, minutesUsed, availableTime);
        }
        slideLimit = _this.limits[i];
        return availableTime += slideLimit;
      });
    };

    Clock.prototype.updateCss = function(clock, used, avail) {
      clock.removeClass('moody happy angry');
      if (avail > used + 5) {
        return clock.addClass('happy');
      } else if (avail > used) {
        return clock.addClass('moody');
      } else {
        return clock.addClass('angry');
      }
    };

    Clock.prototype.appendClockToCurrentSlide = function() {
      var clock;
      var _this = this;
      this.slides = $('div.slide .content');
      if (this.slides.length !== this.limits.length) {
        console.log("Warning: there is " + this.slides.length + " written slides while the limit is only configured with " + this.limits.length + " slides");
      }
      clock = $('<div>').addClass('clock');
      $.each(this.slides, function(i, slide) {
        if (_this.isCurrentSlide(slide)) return $(slide).append(clock);
      });
      return clock;
    };

    Clock.prototype.isCurrentSlide = function(slide) {
      return $(slide).parent().css('display') === 'table';
    };

    return Clock;

  })();

  obj = new Clock();

  setTimeout(obj.appendClockToCurrentSlide, 1000);

  setInterval(obj.updateClock, 1000);

}).call(this);
