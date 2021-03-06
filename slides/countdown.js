(function() {
  var Clock, obj;
  var __bind = function(fn, me){ return function(){ return fn.apply(me, arguments); }; };

  Clock = (function() {

    function Clock() {
      this.appendClockToCurrentSlide = __bind(this.appendClockToCurrentSlide, this);
      this.updateClock = __bind(this.updateClock, this);      this.limits = [3, 3, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 4, 3, 7, 35, 0, 2, 2, 2, 2, 2, 2, 2, 0, 5, 5, 10, 9, 5, 3, 9, 4, 9, 6, 4, 9, 4, 9, 4, 9, 4, 4, 9, 2, 4, 9, 4];
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
        slideLimit = _this.limits[i];
        availableTime += slideLimit;
        if (_this.isCurrentSlide(slide)) {
          slide = $(slide);
          clock = slide.find('div.clock');
          if (!(clock.length > 0)) clock = _this.appendClockToCurrentSlide();
          if (availableTime) {
            clock.html("" + minutesUsed + "/" + availableTime);
          } else {
            if (i !== 0) {
              clock.html($("<img>").attr('src', 'images/homer.jpg').css('height', '80px'));
            }
          }
          return _this.updateCss(clock, minutesUsed, availableTime);
        }
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
