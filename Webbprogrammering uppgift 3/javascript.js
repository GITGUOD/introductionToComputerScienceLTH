function loopText(){
	var tecken = document.getElementById("tecken").value;
	var antal = document.getElementById("antal").value;
	
	let text ="";

	
	if(tecken.length>1)
	{
		text="Skriv bara ett tecken!";
	}
	else{
		for(let i=0; i <antal;i++)
		{
			for(let i=0; i<antal;i++)
			{
				text += tecken;
			}
			text += "<br>";
	}
	}
	
	document.getElementById("text").innerHTML = text;
}