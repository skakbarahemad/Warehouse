
    $(document).ready(function(){
//        $("#shipModeError").hide();
        $("#shipCodeError").hide();
        $("#enableShipError").hide();
        $("#shipGradeError").hide();
        $("#shipDescError").hide();

        var shipModeError = false;
        var shipCodeError = false;
        var enableShipError = false;
        var shipGradeError = false;
        var shipDescError = false;

        function validate_shipMode(){
            var val = $("#shipMode").val();
            if(val==''){
                $("#shipModeError").show();
                $("#shipModeError").html('*Please select Mode');
                $("#shipModeError").css('color','red');
                shipModeError = false;
            }
            else{
                $("#shipModeError").hide();
                shipModeError = true;
            }
            return shipModeError;
        }

        function validate_shipCode(){
            var val = $("#shipCode").val();
            var exp = /^[A-Za-z\-\s]{4,8}$/
            if(val==''){
                $("#shipCodeError").show();
                $("#shipCodeError").html('*Please enter <b>Code</b>');
                $("#shipCodeError").css('color','red');
                shipCodeError= false;
            }
            else if(!exp.test(val)){
            	$("#shipCodeError").show();
                $("#shipCodeError").html('*Code must be 4-8 uppercase/lowercase letters, special characters not allowed except"-"');
                $("#shipCodeError").css('color','red');
                shipCodeError= false;
            }
            else{
            	var id=0;
            	if($("#id").val()!=undefined){
            		id=$("#id").val();
            	}
            	$.ajax({
            		url:"validate",
            		data:{"shipCode":val,"id":id},
            		success:function(resTxt){
            			if(resTxt!=''){
            				$("#shipCodeError").show();
            				$("#shipCodeError").html(resTxt);
                            $("#shipCodeError").css('color','red');
                            shipCodeError= false;
            			}
            			else{
            				$("#shipCodeError").hide();
                            shipCodeError= true;
            			}
            		}
            	});
                
            }
            return shipCodeError;
        }

        function validate_enableShip(){
            var len = $('[name="enableShip"]:checked').length;
            if(len==0){
                $("#enableShipError").show();
                $("#enableShipError").html('*Please Select anyone');
                $("#enableShipError").css('color','red');
                enableShipError = false;
            }
            else{
                $("#enableShipError").hide();
                enableShipError=true;
            }
            return enableShipError;
        }

        function validate_shipGrade(){
            var len = $('[name="shipGrade"]:checked').length;
            if(len==0){
                $("#shipGradeError").show();
                $("#shipGradeError").html('*Please select anyone');
                $("#shipGradeError").css('color','red');
                shipGradeError=false;
            }
            else{
                $("#shipGradeError").hide();
                shipGradeError = true;
            }
            return shipGradeError;
        }

        function validate_shipDesc(){
            var val = $("#shipDesc").val();
            if(val==''){
                $("#shipDescError").show();
                $("#shipDescError").html('*Please enter the Desc');
                $("#shipDescError").css('color','red');
                shipDescError=false;
            }
            else{
                $("#shipDescError").hide();
                shipDescError=true;
            }
            return shipDescError;
        }

        $("#shipMode").change(function(){
            validate_shipMode();
        });
        $("#shipCode").keyup(function(){
            validate_shipCode();
        });
        $('[name="enableShip"]').change(function(){
            validate_enableShip();
        });
        $('[name="shipGrade"]').change(function(){
            validate_shipGrade();
        });
        $("#shipDesc").keyup(function(){
            validate_shipDesc();
        })

        $("#ShipmentTypeRegister").submit(function(){
            validate_shipMode();
            validate_shipCode();
            validate_enableShip();
            validate_shipGrade();
            validate_shipDesc();

            if(shipModeError && shipCodeError && enableShipError && shipGradeError && shipDescError)
                return true;         
            else
                return false;            
        });
    });

   