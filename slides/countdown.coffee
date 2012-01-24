class Clock
  constructor: ->
    @limits = [
      3, #intro
      15, #tommyintro
      10, # agenda
      5, # flere nivå 
      5, # unit-testing
      5, # funksjonell-testing
      7, # play framework-bakrgunn
      8,
      9,
      10,
      11,
      12,
      13,
      14,
      15,
      16,
      17,
      18,
      19,
      20,
      21,
      22,
      23,
      24]
    @startTime = new Date()

  updateClock: =>
    millisecondsUsed = new Date().getTime() - @startTime.getTime()
    minutesUsed = Math.floor(millisecondsUsed / 1000 ) # / 60)
    availableTime = 0
    
    $.each(@slides, (i, slide) =>
      if @isCurrentSlide(slide)
        slide = $(slide)
        clock = slide.find('div.clock')
        unless clock.length > 0
          clock = @appendClockToCurrentSlide()

        clock.html("#{minutesUsed}/#{availableTime}")
        @updateCss(clock, minutesUsed, availableTime)

      slideLimit = @limits[i]
      availableTime += slideLimit
    )

  updateCss: (clock, used, avail) ->
    clock.removeClass('moody happy angry')
    if avail > used + 5
      clock.addClass('happy')
    else if avail > used
      clock.addClass('moody')
    else
      clock.addClass('angry')

  appendClockToCurrentSlide: =>
    @slides = $('div.slide .content')
    clock = []
    $.each(@slides, (i, slide) =>
      if @isCurrentSlide(slide)
        clock = $('<div>').addClass('clock')
        $(slide).append(clock)
    )

    return clock

  isCurrentSlide: (slide) ->
    return $(slide).parent().css('display') == 'table'

obj = new Clock()
setTimeout(obj.appendClockToCurrentSlide, 1000)
setInterval(obj.updateClock, 1000)
