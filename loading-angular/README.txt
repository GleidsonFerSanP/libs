LOADING ANGULAR JS

1 - importar o modulo na app
var app = angular.module('formVendaSeuTerreno', ['ui.mask','ngLoadingSpinner']);

2 - Includes Js
<script type="text/javascript" src="http://fgnass.github.io/spin.js/spin.min.js"></script>
<script src="<ilion:link uri=''/>js/angular/angular-spinner-min.js" type="text/javascript" charset="UTF-8"></script>
<script src="<ilion:link uri=''/>js/angular/angular-spinner-directive.js" type="text/javascript" charset="UTF-8"></script>

3 - Uso 
<span us-spinner="{radius:30, width:8, length: 16}"></span>
