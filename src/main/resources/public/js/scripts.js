$(document).ready(function(){
    var  checkAndShow = function (){
        return ($('.check').is(':checked'));
    }

    $('.check').on('change', function(e){
        if(checkAndShow()){
            //show your div here
        }
        else{
            //hide your div here
        }
    });
});