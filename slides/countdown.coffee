class Clock
  constructor: ->
    @limits = [
      3, #intro
      15, #tommyintro
      10, # agenda
      3, # testing intro med bilde
      3, # hvorfor tester vi?
      3, # unit-testing
      3, # funksjonell-testing
      3, # akseptansetester
      1, # flere nivå
      5, # play framework fordeler/ulemper
      5, #play arkitektur
      5, #demo 
      35, # Oppsett, minnebrikker osv, med mat
      8, # tdd  1
      7, # tdd 2
      4, # tdwd intro
      5, # intro til oppgave
      5, # oppsett
      15, # iterasjon 1
      10, # iterasjon 2
      10, # iterasjon 3
      10, # iterasjon 4
      10, # iterasjon 5
      10, # iterasjon 6
      10, # iterasjon 7
      10, # iterasjon 8
      10, # iterasjon 9
      10 # iterasjon 10
    ]
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
    if @slides.length isnt @limits.length
      console.log("Warning: there is #{@slides.length} written slides while the limit is only configured with #{@limits.length} slides")
    clock = $('<div>').addClass('clock')
    $.each(@slides, (i, slide) =>
      if @isCurrentSlide(slide)
        $(slide).append(clock)
    )

    return clock

  isCurrentSlide: (slide) ->
    return $(slide).parent().css('display') == 'table'

obj = new Clock()
setTimeout(obj.appendClockToCurrentSlide, 1000)
setInterval(obj.updateClock, 1000)
