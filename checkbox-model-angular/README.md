checkbox model angular

1 - Site da lib
http://vitalets.github.io/checklist-model/

2 - importar o arquivo ckecklist-model.js

3 - Modo de uso

<div  ng-repeat="pr in previlegios track by pr.id" class=" col-xs-6">

   <input type="checkbox" checklist-model="p.permissoes" checklist-value="pr">
   <span ng-bind="previlegios[$index].descricao"></span>

</div>                                              

