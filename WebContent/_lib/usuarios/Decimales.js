function CerosDecimales(event){
	
	var numero = parseInt(event.value);
	var tamanio = (7-(numero.toString().length));
	var ceros = '0000.00';
	var resultado = '';
	
	if(parseInt(numero)<0){
		resultado=ceros;
		$('.credito').val(resultado);
		return;
	}
	
	if(isNaN(numero)){
		resultado = ceros;
		$('.credito').val(resultado)
		return;
		
	}
	
	if (tamanio == 7){
		resultado = numero;
	}
	
	else if (tamanio > String(ceros).length){
		 resultado = numero;
	}
	else if(numero.toString().includes('.')){
		
		
		if(tamanio == 4){
			
			if(isNaN(numero)){
				
				resultado = String(numero).substring(0,2)+'00';
			}else{
				resultado = ceros;
				
			}
		}
	}else{
		
		resultado = String(numero).substring(0,4)+'.00';
	}
	$('.razon').prop('disabled',false);
	$('.credito').val(resultado);
	
}
