function valida() {
    const senha1 = document.querySelector("#senha").value;
    const senha2 = document.querySelector("#senha1").value;


    if (senha1 != "" && senha2 != "" && senha1!= null && senha2 != null && senha1 === senha2)
    {
        console.log("testou essa porra" );
        document.getElementById('botao').disabled=false;
    }
    else
    {

    }
}

