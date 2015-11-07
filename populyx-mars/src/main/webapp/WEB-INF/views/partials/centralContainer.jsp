	<div class="containerCentered" id="page">
		<div ng-controller="TemplateController">
			<select ng-model="template" ng-options="t.name for t in templates">
				<option value="">(blank)</option>
			</select> url of the template:
			<code>{{template.url}}</code>
			<hr />
			<div>
				<div ng-animate="{enter: 'animate-enter', leave: 'animate-leave'} "
					ng-include="template.url"></div>
			</div>
		</div>
		<div class="clearfix grpelem" id="u150-4">
			<!-- content -->
			<p id="u150-2">
				<strong class="Important" id="u150">Main Road 123, Main
					City, 48374, USA</strong>
			</p>
		</div>
	</div>