package br.com.seg.utils;

public class ValidaDocumento {
	private String documento;
	
	public ValidaDocumento(String C, String tipDoc) {
		this.documento = ValidaDocumento.Format(C,false,tipDoc);
	}

	public static String LimpaDoc(String C, String tipDoc) {
		String ret = "";
		try {
			ret = Format(C,false,tipDoc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public boolean isCNPJ(){

		if (this.documento.equals("00000000000000") || 
				this.documento.equals("11111111111111") ||
				this.documento.equals("22222222222222") || 
				this.documento.equals("33333333333333") ||
				this.documento.equals("44444444444444") || 
				this.documento.equals("55555555555555") ||
				this.documento.equals("66666666666666") || 
				this.documento.equals("77777777777777") ||
				this.documento.equals("88888888888888") || 
				this.documento.equals("99999999999999") ||
				this.documento.length() != 14)
			return(false);

		char dig13, dig14;
		int sm, i, r, num, peso;

		try {
			// Calculo do primeiro Digito Verificador
			sm = 0;
			peso = 2;
			for (i=11; i>=0; i--) {
				num = (int)(this.documento.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso + 1;
				if (peso == 10)
					peso = 2;
			}

			r = sm % 11;
			if ((r == 0) || (r == 1))
				dig13 = '0';
			else dig13 = (char)((11-r) + 48);

			// Calculo do segundo Digito Verificador
			sm = 0;
			peso = 2;
			for (i=12; i>=0; i--) {
				num = (int)(this.documento.charAt(i)- 48);
				sm = sm + (num * peso);
				peso = peso + 1;
				if (peso == 10)
					peso = 2;
			}

			r = sm % 11;
			if ((r == 0) || (r == 1))
				dig14 = '0';
			else dig14 = (char)((11-r) + 48);


			if ((dig13 == this.documento.charAt(12)) && (dig14 == this.documento.charAt(13)))
				return(true);
			else
				return(false);
		} catch (Exception e) {
			return(false);
		}
	}

	public String getdocumento(boolean Mascara,String tipDoc) {
		return Format(this.documento,Mascara,tipDoc);
	}

	private static String Format(String documento, boolean Mascara,String tipDoc){
		if(tipDoc == "CNPJ") {
			if(Mascara){
				return(documento.substring(0, 2) + "." + documento.substring(2, 5) + "." +
						documento.substring(5, 8) + "/" + documento.substring(8, 12) + "-" +
						documento.substring(12, 14));
			}else{
				documento = documento.replace(".","");
				documento = documento.replace("-","");
				documento = documento.replace("/","");
				return documento;
			}
		} else {
			if(Mascara){
				return(documento.substring(0, 3) + "." + documento.substring(3, 6) + "." +
						documento.substring(6, 9) + "-" + documento.substring(9, 11));
			}else{
				documento = documento.replace(".","");
				documento = documento.replace("-","");
				return documento;
			}
		}

	}

	public boolean isCPF(){

			if (this.documento.equals("00000000000") || 
					this.documento.equals("11111111111") || 
					this.documento.equals("22222222222") || 
					this.documento.equals("33333333333") ||
					this.documento.equals("44444444444") ||
					this.documento.equals("55555555555") ||
					this.documento.equals("66666666666") ||
					this.documento.equals("77777777777") ||
					this.documento.equals("88888888888") ||
					this.documento.equals("99999999999") ||
					this.documento.length() != 11)
				return(false);

			char dig10, dig11; 
			int sm, i, r, num, peso; 

			try { 
				// Calculo do primeiro Digito Verificador 
				sm = 0; 
				peso = 10; 
				for (i=0; i<9; i++) { 
					num = (int)(this.documento.charAt(i) - 48); 
					sm = sm + (num * peso); 
					peso = peso - 1;
				} 
				r = 11 - (sm % 11); 
				if ((r == 10) || (r == 11)) 
					dig10 = '0'; 
				else 
					dig10 = (char)(r + 48); 

				// Calculo do segundo Digito Verificador 
				sm = 0; 
				peso = 11; 
				for(i=0; i<10; i++) { 
					num = (int)(this.documento.charAt(i) - 48);
					sm = sm + (num * peso); 
					peso = peso - 1;
				} 
				r = 11 - (sm % 11); 
				if ((r == 10) || (r == 11)) 
					dig11 = '0'; 
				else 
					dig11 = (char)(r + 48); 

				if ((dig10 == this.documento.charAt(9)) && (dig11 == this.documento.charAt(10))) 
					return(true); 
				else return(false);
			} catch(Exception e) { 
				return(false); 
			} 
		}
  
}

