/* 
    Document   : functions.js
    Created on : Oct 11, 2012, 01:11:41 PM
    Author     : Anders
    Description: Functions using Jquery
 */

// This is a bug-fix.
var $ = jQuery;    
$(document).ready(function() {    
 
    // Handles the effects of the main menu.
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
    });  // toggle end 
}); // document ready end


$(document).ready(function() {
    $(function() {
        var availableTags = [
        "Arlanda Airport (Stockholm)",
        
        "Amsterdam Airport Schiphol",
        
        "Bangkok International Airport",
        
        "Beijing Capital International Airport",
        
        "Dubai International Airport",
        
        "Frankfurt Airport",
        
        "Haneda Airport (Tokyo)",
        
        "Heathrow Airport (London)",
        
        "Hong Kong International Airport",
        
        "O'Hare International Airport (Chicago)",
        
        "John F. Kennedy International Airport (New York City)",
        
        "Landvetter Airport (Gothenburg)",
        
        "Leonardo da Vinci-Fiumicino Airport (Rome)",
        
        "London Gatwick Airport",
        
        "Los Angeles International Airport",
        
        "Madrid Barajas Airport",
        
        "McCarran International Airport (Las Vegas)",
        
        "Munich Airport",
        
        "Newark Liberty International Airport (New Jersey)",

        "Paris-Charles de Gaulle Airport",
        
        "San Francisco International Airport",

        "Singapore Changi Airport",
        ];
        $( ".autoCompleteInputForm" ).autocomplete({
            source: availableTags
        });
    });
    
});
