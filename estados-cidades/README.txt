1 - Importar javascript 

<script src="js/cidades-estados-v0.2.js"></script>

2 - Adicione um id no select de estados e no de cidades nesse formato

<select name="estado" class="form-control" id="estado">
	<option value="">Selecione o Estado</option>
</select>

<select name="cidade" class="form-control" id="cidade">

	<option value="">Selecione a Cidade</option>

</select>
	
3 - Inicie o script desta maneira

new dgCidadesEstados(
	document.getElementById('estado'), 
	document.getElementById('cidade'), 
	true
	);				