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


//This adds an autocomplete function to the From and To fields.
//delay a bit in the textfield after entering a letter...
$(document).ready(function() {
    $(function() {
        var availableTags = [
        "Arlanda (Stockholm)",
        
        "Schiphol (Amsterdam)",
        
        "Bangkok International",
        
        "Beijing Capital International",
        
        "Dubai International",
        
        "Frankfurt Airport",
        
        "Haneda Airport (Tokyo)",
        
        "Heathrow (London)",
        
        "Hong Kong International",
        
        "O'Hare (Chicago)",
        
        "John F. Kennedy (New York City)",
        
        "Landvetter (Gothenburg)",
        
        "Leonardo da Vinci-Fiumicino (Rome)",
        
        "London Gatwick",
        
        "Los Angeles International",
        
        "Madrid Barajas",
        
        "McCarran International (Las Vegas)",
        
        "Munich Airport",
        
        "Newark Liberty (New Jersey)",

        "Paris-Charles de Gaulle",
        
        "San Francisco International",

        "Singapore Changi",
        ];
        $( ".autoCompleteInputForm" ).autocomplete({
            source: availableTags
        });
    });
});