$(function(){
			
			  
		        $('#countdown-2').timeTo(900, function(){
		        	$('#diviframevisa').hide(1000);
		        	$('#divtiempo').css({display:'block'});
		        });
		       
		   /*     $('#reset-1').click(function() {
		            $('#countdown-1').timeTo('reset');
		        }); */
		        /**
		         * Hide hours
		         */
		        $('#countdown-2').timeTo({
		            seconds: 900,
		            displayHours: false
		        });
		        //var date = getRelativeDate(20);
		        //document.getElementById('date-str').innerHTML = date.toString();
		        /**
		         * Set timer countdown to specyfied date
		         */
		        //$('#countdown-2').timeTo(date);
		        //date = getRelativeDate(7, 9);
		    //    document.getElementById('date2-str').innerHTML = date.toString();
		        /**
		         * Set theme and captions
		         */
		        $('#countdown-2').timeTo({
		            timeTo: date,
		            displayDays: 2,
		            theme: "black",
		            displayCaptions: true,
		            displayHours: false,
		            fontSize: 48,
		            captionSize: 14,
		            width: 15,
		            lang: 'en',
		        });
		        /**
		         * Simple digital clock
		         */
		     //   $('#clock-1').timeTo();
		        function getRelativeDate(days, hours, minutes){
		            var date = new Date((new Date()).getTime() + 60000 /* milisec */ * 60 /* minutes */ * 24 /* hours */ * days /* days */);
		            date.setHours(hours || 0);
		            date.setMinutes(minutes || 0);
		            date.setSeconds(0);
		            return date;
		        }
			
			
		});