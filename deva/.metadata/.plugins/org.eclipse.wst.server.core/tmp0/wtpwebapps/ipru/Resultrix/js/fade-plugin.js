(function ($) {
  $.fn.fadeTransition = function(options) {
    var item = null,
        options = $.extend({pauseTime: 5000,
                            transitionTime: 2000,
                            ignore: null,
                            delayStart: 0,
                            pauseOnMouseOver: false,
                            manualNavigation: false,
                            createNavButtons: false,
                            navButtonContainer: null}, options);
                            
    this.each(function() {
      if (typeof(this.fader) != 'undefined') {
        item = this.fader;
      }
    });
      
    if (item) {
      return item;
    }
                            
    function fader(obj) {
      var timer = null,
          current = 0;
          els = (options.ignore)?$("> *:not(" + options.ignore + ")", obj):$("> *", obj);
      
      function setup() {
        $(obj).css("position", "relative");
        els.css("display", "none").css("left", "0").css("top", "0").css("position", "absolute");
        
        if (options.createNavButtons) {
          createNavButtons();
        }
      
        if (options.pauseOnMouseOver) {
          $(obj).mouseover(pause).mouseout(cue);
        }
      
        if (options.delayStart > 0) {
          setTimeout(showFirst, options.delayStart);
        }
        else {
          showFirst();
        }
      }
      
      setup();
      
      function createNavButtons() {
        var i, nav = $('<div class="fadenav"></div>');
        for (i=0; i<els.length; i++)
          $('<a class="nav' + i + '" href="#">&nbsp;</a>').click(manualNav).appendTo(nav);
          
        nav.appendTo(options.navButtonContainer || obj);
      }
      
      function manualNav(e) {
        var item;
        e = e || window.event;
        $(e.target).blur();
        item = $('.fadenav a', obj).index(e.target);
        if (timer) {
          clearTimeout(timer);
        }
        transition(item);
        return false;
      }
      
      function pause() {
        if (timer)
          clearTimeout(timer);
      }
      
      function highlightNav() {
        if (options.createNavButtons) {
          $('.fadenav a', options.navButtonContainer || obj).removeClass('current');
          $('.fadenav a:nth-child(' + (1 + current) + ')', options.navButtonContainer || obj).addClass('current');
        }
      }
      
      function showFirst() {
        if (options.ignore) {
          $(options.ignore, obj).fadeOut(options.transitionTime);
          $(els[current]).fadeIn(options.transitionTime, cue);
        }
        else {
          $(els[current]).css("display", "block");
        }
        
        highlightNav();
      }

      function transition(next) {
        $(els[current]).fadeOut(options.transitionTime);
        $(els[next]).fadeIn(options.transitionTime);
        current = next;
        highlightNav();
        cue();
      }
      
      this.show = function(item) {
        if (typeof(els[item]) != 'undefined') {
          if (timer)
            clearTimeout(timer);
            
          transition(item);
        }
        
        return this;
      };
      
      function cue() {
        if ($("> *", obj).length < 2) {
          return false;
        }
        
        if (timer) {
          clearTimeout(timer);
        }
        
        if (!options.manualNavigation) {
          timer = setTimeout(function() { transition((current + 1) % els.length | 0)} , options.pauseTime);
        }
      }
      
      return obj.fader = this;
    }

    return this.each(function() {
      return (typeof(this.fader) != 'object')?new fader(this):this.fader;
    });
  }

})(jQuery);

