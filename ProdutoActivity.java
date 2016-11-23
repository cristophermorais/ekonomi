//MainActivity produto - precisa de alterações


public class Produto extends Ekonomi{
	
	private DBHelper dh;
	EditText etNome, etEnd, etEmpresa;
	Button btInserir, btListar;

	.............
	public void onCreate(){
		.............
		this.db = new DBHelper(this);
		etNome = (EditText) findViewById(R.id.etNome);
		etUnMedida = (EditText) findViewById(R.id.etEnd);
		etPreco = (EditText) findViewById(R.id.etEmpresa);
		
		btInserir = (button) findViewById(R.id.btInserir);
		btListar = (button) findViewById(R.id.btListar);
		
		
		
		//Cadastro de produtos realizado pelo usuário - modificar campos
		btInserir.setOnClickListener(new View.OnClickListener(){
			@override
			public void onClick(View v){
				if(etNome.getText().length() > 0 && etUnMedida.getText().length() > 0 && etPreco.getText().length() > 0){
					dh.insert();
					AlertDialog.Biulder adb = new AlertDialog.Biulder(MainActivity.this);
					adb.setTittle("Sucesso");
					adb.setMessage("Cadastro realizado");
					adb.show();
					
					etNome.setText("");
					etEnd.setText("");
					etEmpresa.setText("");
					
				}else{
					AlertDialog.Biulder adb = new AlertDialog.Biulder(MainActivity.this);
					adb.setTittle("Erro");
					adb.setMessage("Todos os campos devem ser preenchidos");
					adb.show();
					
					etNome.setText("");
					etEnd.setText("");
					etEmpresa.setText("");
				
				
				}
			}
			
		});
				
		
		//Listagem de produtos - Modificar campos
		btListar.setOnClickListener(new View.OnClickListener(){
		@override		
		public void onClick(View v){
			List <Lista> lista = dh.queryGetAll();
			
			if(lista == null){
				AlertDialog.Biulder adb = new AlertDialog.Biulder(MainActivity.this);
				adb.setTittle("Mensagem");
				adb.setMessage("Todos os campos devem ser preenchidos");
				adb.show();
			}
			for(int i=0; i<lista.size(); i++){
				AlertDialog.Biulder adb = new AlertDialog.Biulder(MainActivity.this);
				adb.setTittle("Registro "+ i);
				adb.setMessage("Nome "+ lista.getNome()+"\nUnidade de medida: "+ lista.getUnMedida()+"\nPreço: "+ lista.getPreco());
				//adb.setPositiveButton("OK", new DialogInterface);
				@override
				public void onClick(DialogInterface dialog, int which){
										
					dialog.dismiss();
				}
				
			});
				
				
		}
		
		
		}
		
		
		
	}
	

}
