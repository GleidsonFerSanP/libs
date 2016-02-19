LOADING ANGULAR JS

1 - importar o modulo na app
var app = angular.module('APP', ['ngLoadingSpinner']);

2 - Includes Js
<script type="text/javascript" src="http://fgnass.github.io/spin.js/spin.min.js"></script>
<script src="js/angular-spinner-min.js"></script>
<script src="js/angular-spinner-directive.js"></script>

3 - Uso 
<span us-spinner="{radius:30, width:8, length: 16}"></span>
