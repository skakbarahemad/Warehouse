$(document).ready(function(){
	$("#uomTypeError").hide();
	$("#uomModelError").hide();
	$("#uomDescError").hide();

	var uomTypeError=false;
	var uomModelError= false;
	var uomDescError= false;

	function validate_uomType(){
		var val = $("#uomType").val();
		if(val==''){
			$("#uomTypeError").show();
			$("#uomTypeError").html('*Please select <b>Type</b>');
			$("#uomTypeError").css('color','red');
			uomTypeError = false;
		}
		else{
			$("#uomTypeError").hide();
			uomTypeError = true;
		}
		return uomTypeError;
	}

	function validate_uomModel(){
		var val=$("#uomModel").val();
		var exp = /^[A-Za-z0-9\-\s]{4,8}$/
			if (val==''){
				$("#uomModelError").show();
				$("#uomModelError").html('*Please enter <b>Model</b>');
				$("#uomModelError").css('color','red');
				uomModelError=false;
			}
			else if(!exp.test(val)){
				$("#uomModelError").show();
				$("#uomModelError").html('*Model must be Min 4 char and Max 8 chars');
				$("#uomModelError").css('color','red');
				uomModelError=false;
			}
			else{
				var id=0;
				if($("#uomId").val()!=undefined){
					id=$("#uomId").val();
				}
				$.ajax({
					url:"validate",
					data:{"uomModel":val,"id":id},
					success:function(resTxt){
						if(resTxt!=''){
							$("#uomModelError").show();
							$("#uomModelError").html(resTxt);
							$("#uomModelError").css('color','red');
							uomModelError=false;
						}
						else{
							
							$("#uomModelError").hide();
							uomModelError=true;
						}
					}
				});
			}

	
	return uomModelError;
}

function validate_uomDesc(){
	var val = $("#uomDesc").val();
	if(val==''){
		$("#uomDescError").show();
		$("#uomDescError").html('*Please enter <b>Desc</b>');
		$("#uomDescError").css('color','red');
		uomDescError = false;
	}
	else{
		$("#uomDescError").hide();
		uomDescError =true;
	}
	return uomDescError;
}

$("#uomType").change(function(){
	validate_uomType();
});

$("#uomModel").keyup(function(){
	$(this).val($(this).val().toUpperCase());
	validate_uomModel();
});
$("#uomDesc").keyup(function(){
	$(this).val($(this).val().toUpperCase());
	validate_uomDesc();
});




$("#uomTypeRegister").submit(function(){
	validate_uomType();
	validate_uomModel();
	validate_uomDesc();

	if(uomTypeError && uomModelError && uomDescError)
		return true;
	else
		return false;
});
});