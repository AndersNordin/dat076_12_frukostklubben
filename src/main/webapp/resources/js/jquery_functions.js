var $ = jQuery;
    
$(document).ready(function() {
    
    $("#toggle_arrow").click(function () {
        var elem = "nav#main_menu";
        $(elem).animate({
            width: 'toggle'
        },'fast');
        
        var currentClass = $(this).attr('class');
        if(currentClass == 'arrow_right'){
            $(this).removeClass('arrow_right');
            $(this).addClass("arrow_left");   
        }else
        {        
            $(this).removeClass('arrow_left');                
            $(this).addClass("arrow_right");                
        }           
        
                 
                 
    });   
 
});