$(function(){
			
			  
		        $('#countdown-2').timeTo(1800, function(){
		        	
		        	var titulo = ($("#oculto").val() === undefined || $("#oculto").val() == "SMS") ? 
		        					"Su tiempo ha culminado, usted regresara al Paso 1." : "Su tiempo ha culminado, porfavor ingrese de nuevo.";
		        	
					swal(
							 {
								 title : titulo,
								 type : 'warning',
								 confirmButtonColor: '#00822E'
							 }
							).then(function(){
								window.location = ($("#oculto").val() === undefined || $("#oculto").val() == "SMS") ? "venta" : "inicio";
							}, function(dismiss){
								window.location = ($("#oculto").val() === undefined || $("#oculto").val() == "SMS") ? "venta" : "inicio";
					})  
		            
		           
		            
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
		        /*$('#countdown-2').timeTo({
		            timeTo: date,
		            displayDays: 2,
		            theme: "black",
		            displayCaptions: true,
		            displayHours: false,
		            fontSize: 48,
		            captionSize: 14,
		            width: 15,
		            lang: 'en',
		        });*/
		        /**
		         * Simple digital clock
		         */
		     //   $('#clock-1').timeTo();
		        /*function getRelativeDate(days, hours, minutes){
		            var date = new Date((new Date()).getTime() + 60000 * 60  * 24 * days );
		            date.setHours(hours || 0);
		            date.setMinutes(minutes || 0);
		            date.setSeconds(0);
		            return date;
		        }*/
			
			
		});