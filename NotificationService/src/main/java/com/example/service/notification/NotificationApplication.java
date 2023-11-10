package com.example.service.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotificationApplication {

	public static void main(String[] args) {

		SpringApplication.run(NotificationApplication.class, args);
	}

}
/*

"name":"aktivacioni",
"params":"Pozdrav @name @surname aktivirajte svoj nalog @username klikom na link",
"podaci":"@name,@surname,@username"



"name":"promenalozinke",
"params":"Pozdrav @name @surname promenite lozinku za nalog @username klikom na link",
"podaci":"@name,@surname,@username"




"name":"rezervisano",
"params": "Rezervisali ste vozilo modela @model na @brojdana do @datuma",
"podaci": "@model,@brojdana,@datum"




"name":"otkazano",
"params": "Otkazali ste vozilo modela @model" ,
"podaci": "@model"




"name":"podsetnik",
"params": "Podsecam vas da ste vozilo modela @model na @brojdana rezervisali do @datuma",
"podaci": "@model,@brojdana,@datum"


 */