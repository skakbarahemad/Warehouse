$(document).ready(function() {
	$("#orderModeError").hide();
	$("#orderCodeError").hide();
	$("#orderTypeError").hide();
	$("#orderAcptError").hide();
	$("#orderDescError").hide();

	var orderModeError = false;
	var orderCodeError = false;
	var orderTypeError = false;
	var orderAcptError = false;
	var orderDescError = false;

	function orderMode_validate() {
		var len = $('[name="orderMode"]:checked').length;
		if (len == 0) {
			$("#orderModeError").show();
			$("#orderModeError").html('*Please select <b>Mode</b>');
			orderModeError = false;
		}
		else {
			$("#orderModeError").hide();
			orderModeError = true;
		}
		return orderModeError;
	}

	function validate_orderCode() {
		var val = $("#orderCode").val();
		var exp = /^[A-Za-z]{4,10}$/
			if (val=='') {
				$("#orderCodeError").show();
				$("#orderCodeError").html('*Please enter <b>Code</b>');
				orderCodeError = false;
			}
			else if(!exp.test(val)){
				$("#orderCodeError").show();
				$("#orderCodeError").html('*Code must be min 4 and max 10 char');
				orderCodeError = false;
			}
			 else{
	            	var id=0;
	            	if($("#id").val()!=undefined){
	            		id=$("#id").val();
	            	}
	            	$.ajax({
	            		url:'validate',
	            		data:{"orderCode":val,"id":id},
	            		success:function(resTxt){
	            			if(resTxt!=''){
							$("#orderCodeError").show();
							$("#orderCodeError").html(resTxt);
							orderCodeError = false;
						}
						else{
							$("#orderCodeError").hide();
							orderCodeError = true;
						}
				 $("#orderCodeError").hide();
					orderCodeError = true;
					}

				});

			}
		return orderCodeError;
	}

	function validate_orderType(){
		var val=$('[name="orderType"]').val();
		if(val==''){
			$("#orderTypeError").show();
			$("#orderTypeError").html('*Please select anyone Type');
			orderTypeError = false;
		}
		else{
			$("#orderTypeError").hide();
			orderTypeError = true;
		}
	}
	function validate_orderAcpt(){
		var len = $('[name="orderAcpt"]:checked').length;
		if(len == ''){
			$("#orderAcptError").show();
			$("#orderAcptError").html("*Please choose one");
			orderAcptError = false;
		}
		else{
			$("#orderAcptError").hide();
			orderAcptError = true;
		}
		return orderAcptError;
	}

	function validate_orderDesc(){
		var val = $("#orderDesc").val();
		if(val==''){
			$("#orderDescError").show();
			$("#orderDescError").html('*Please enter Description');
			orderDescError = false;
		}

		else {
			$("#orderDescError").hide();
			orderDescError = true;
		}
		return orderDescError;
	}


	$('[name="orderMode"]').change(function(){
		orderMode_validate();
	});

	$("#orderCode").keyup(function(){
		$(this).val($(this).val().toUpperCase());               
		validate_orderCode();
	});
	$('[name="orderType"]').change(function(){
		validate_orderType();
	});

	$('[name="orderAcpt"]').change(function(){
		validate_orderAcpt(); 
	});
	$("#orderDesc").keyup(function(){
		$(this).val($(this).val().toUpperCase());
		validate_orderDesc();
	});

	$("#orderMethodForm").submit(function () {
		orderMode_validate();
		validate_orderCode();
		validate_orderType();
		validate_orderAcpt();
		validate_orderDesc();

		if (orderModeError && orderCodeError && orderTypeError && orderAcptError && orderDescError)
			return true;
		else
			return false;
	});
});