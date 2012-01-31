class Clock
  constructor: ->
    @limits = [
      3, # førsteside
      3, # agenda
      0, # "tom" side
      2, # hvorfor tester vi og ivars opplegg
      2, # hvorfor tester vi og ivars opplegg
      2, # hvorfor tester vi og ivars opplegg
      2, # hvorfor tester vi og ivars opplegg
      2, # hvorfor tester vi og ivars opplegg
      2, # hvorfor tester vi og ivars opplegg
      2, # hvorfor tester vi og ivars opplegg
      2, # hvorfor tester vi og ivars opplegg
      2, # hvorfor tester vi og ivars opplegg
      2, # hvorfor tester vi og ivars opplegg
      2, # hvorfor tester vi og ivars opplegg
      2, # hvorfor tester vi og ivars opplegg
      0, # "tom" side
      4, # play
      3, # play arkitektur
      7, # demo og vise play-struktur
      35,# Oppsett, minnebrikker osv, med mat
      0, # "tom" side
      2, # tdd 1
      2, # tdd 2
      2, # tdd 3
      2, # tdd 4
      2, # tdd 5
      2, # tdd 6
      2, # tdd 7
      0, # "tom" side
      5, # utfordringer ved webapptesting
      5, # intro til oppgave
      10, # oppsett
      9, # iterasjon 1
      5, # iterasjon 1
      3, # hibernate
      9, # iterasjon 2
      4, # iterasjon 2
      9, # iterasjon 3
      6, # iterasjon 3
      4, # iterasjon 3
      9, # iterasjon 4
      4, # iterasjon 4
      9, # iterasjon 5
      4, # iterasjon 5
      9, # iterasjon 6
      4, # iterasjon 6
      4, # selenium
      9, # iterasjon 7
      2, # iterasjon 7
      4, # dom selectors
      9, # iterasjon 8
      4 # iterasjon 8
    ]
    @startTime = new Date()

  updateClock: =>
    millisecondsUsed = new Date().getTime() - @startTime.getTime()
    minutesUsed = Math.floor(millisecondsUsed / 1000  / 60)
    availableTime = 0
    
    $.each(@slides, (i, slide) =>
      slideLimit = @limits[i]
      availableTime += slideLimit
      if @isCurrentSlide(slide)
        slide = $(slide)
        clock = slide.find('div.clock')
        unless clock.length > 0
          clock = @appendClockToCurrentSlide()

        if availableTime
          clock.html("#{minutesUsed}/#{availableTime}")
        else
          if i isnt 0
            clock.html($("<img>").attr('src', 'images/homer.jpg').css('height', '80px'))
        @updateCss(clock, minutesUsed, availableTime)

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
