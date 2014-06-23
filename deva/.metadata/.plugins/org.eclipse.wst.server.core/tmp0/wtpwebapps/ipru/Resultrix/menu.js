var arrowimages={down:['', ''], right:['', '']}
var jqueryslidemenu={
animateduration: {over: 200, out: 200},
buildmenu:function(menuid, arrowsvar){
jQuery(document).ready(function($){
$(" #main_navigation a").removeAttr("title");
var $mainmenu=$("#"+menuid+">ul")
var $headers=$mainmenu.find("ul").parent()
$headers.each(function(i){
var $curobj=$(this)
var $subul=$(this).find('ul:eq(0)')
this._dimensions={w:this.offsetWidth, h:this.offsetHeight, subulw:$subul.outerWidth(), subulh:$subul.outerHeight()}
this.istopheader=$curobj.parents("ul").length==1? true : false
$subul.css({top:this.istopheader? this._dimensions.h+"px" : 0})

var $targetul=$(this).children("ul:eq(0)")
this._offsets={left:$(this).offset().left, top:$(this).offset().top}
var menuleft=this.istopheader? 0 : this._dimensions.w
menuleft=(this._offsets.left+menuleft+this._dimensions.subulw>$(window).width())? (this.istopheader? -this._dimensions.subulw+this._dimensions.w : -this._dimensions.w) + 12 : menuleft
if ($targetul.queue().length<=1) 
if(menuleft==0){
$targetul.css({left:menuleft+"px", width:this._dimensions.subulw+'px'}).removeClass("menu_flip")
}
if(menuleft!=0 && this.istopheader){
$targetul.css({left:menuleft+"px", width:this._dimensions.subulw+'px'}).addClass("menu_flip")
}else{
$targetul.css({left:menuleft+"px", width:this._dimensions.subulw+'px'}).removeClass("menu_flip")
}
$curobj.hover(
function(e){
var $targetul=$(this).children("ul:eq(0)")
this._offsets={left:$(this).offset().left, top:$(this).offset().top}
var menuleft=this.istopheader? 0 : this._dimensions.w
menuleft=(this._offsets.left+menuleft+this._dimensions.subulw>$(window).width())? (this.istopheader? -this._dimensions.subulw+this._dimensions.w : -this._dimensions.w) + 12 : menuleft
if ($targetul.queue().length<=1) 
if(menuleft==0){
$targetul.css({left:menuleft+"px", width:this._dimensions.subulw+'px'}).removeClass("menu_flip").slideDown(jqueryslidemenu.animateduration.over)
}
if(menuleft!=0 && this.istopheader){
$targetul.css({left:menuleft+"px", width:this._dimensions.subulw+'px'}).addClass("menu_flip").slideDown(jqueryslidemenu.animateduration.over)
}else{
$targetul.css({left:menuleft+"px", width:this._dimensions.subulw+'px'}).removeClass("menu_flip").slideDown(jqueryslidemenu.animateduration.over)
}
},
function(e){
var $targetul=$(this).children("ul:eq(0)")
$targetul.slideUp(jqueryslidemenu.animateduration.out)
}
) 
})
$mainmenu.find("ul").css({display:'none', visibility:'visible'})
})
}
}
jqueryslidemenu.buildmenu("main_navigation", arrowimages)
jQuery.noConflict()(function($){
jQuery(document).ready(function($){
var 
speed = 700, 
$wall = $('#portfolio').find('.portfolio-container ul')
;
$wall.masonry({
singleMode: true,
itemSelector: '.one-fourth:not(.invis)',
animate: true,
animationOptions: {
duration: speed,
queue: false
}
});
$('#filterable a').click(function(){
var colorClass = '.' + $(this).attr('class');
if(colorClass=='.all') {
$wall.children('.invis')
.toggleClass('invis').fadeIn(speed);
} else { 
$wall.children().not(colorClass).not('.invis')
.toggleClass('invis').fadeOut(speed);
$wall.children(colorClass+'.invis')
.toggleClass('invis').fadeIn(speed);
}
$wall.masonry();
 return false;
});
});
});
