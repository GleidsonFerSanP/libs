Javamail com inputstream de arquivos

1 - Import lib mais recente do javamail

2 - Adicione as classes ao seu projeto

3 - Uso

EmailComAnexoInputstream e = new EmailComAnexoInputstream();

e.setFrom(ConstantesProp.EMAIL_USUARIO, ConstantesProp.NOME);
		
e.addReplyTo(contato.getEmail(), contato.getNome());
		
if(file != null){
e.setAttachment(file);
}
	
e.addToEmailsConcatenados(emailsTo, ConstantesProp.NOME_EMPRESA);

e.setSubject(assuntoEmail);
		
e.setDateSend(new Date());

e.setHtmlMsg(html);

e.sendMail();
