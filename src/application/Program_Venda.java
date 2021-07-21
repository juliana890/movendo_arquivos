package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Venda;

public class Program_Venda {

	public static void main(String[] args) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Entre com o diretório: "); // C:\\Users\\Trabalho\\Documents\\Cursos Udemy\\JAVA\\Estudos\\Desafios\Vendas CSV\\PENDENTES
		String strPath = sc.nextLine(); 
		
		File path = new File(strPath);
		
		File[] files = path.listFiles(File::isFile);
		
		List<Venda> lista = new ArrayList<>();
		
		for(File file : files) {
			File sourceFile = new File(file.toString());
			String pathOut = sourceFile.getParent();
			File diretorioValidado = new File(pathOut + "\\VALIDADO");
			File diretorioInvalidado = new File(pathOut + "\\INVALIDADO");
			
			try(BufferedReader br = new BufferedReader(new FileReader(sourceFile))){
				String v = br.readLine();
				boolean dirDestino;
				
				while(v != null) {
					String[] vendas = v.split(",");
					int numero = Integer.parseInt(vendas[0]);
					String nome = vendas[1];
					Date data = sdf.parse(vendas[2]);
					double valor = Double.parseDouble(vendas[3]);
					lista.add(new Venda(numero, nome, data, valor));
					v = br.readLine();
				}
				
				if(!lista.isEmpty()) {
					if(!diretorioValidado.exists()) {
						dirDestino = new File(pathOut + "\\VALIDADO").mkdir();
					}
					
					moveArquivo(diretorioValidado, sourceFile);
					System.out.println("Arquivo movido com sucesso: " + sourceFile.getName());
					
				}else {
					if(!diretorioInvalidado.exists()) {
						dirDestino = new File(pathOut + "\\INVALIDADO").mkdir();
					}
					
					moveArquivo(diretorioInvalidado, sourceFile);
					System.out.println("Arquivo movido com sucesso: " + sourceFile.getName());
			    }
				
				//Deletando o arquivo antigo
				sourceFile.deleteOnExit();
				lista.clear();
				
			}
			catch(IOException e) {
				System.out.println("Erro ao ler arquivos: " + e.getMessage());
			}
		}
		
		sc.close();

	}

	public static void moveArquivo(File diretorio, File sourceFile) throws IOException {
		//Arquivo que será gerado na pasta
		File moveArquivo = new File(diretorio + "\\" + sourceFile.getName());
		moveArquivo.createNewFile();
		
		InputStream in = new FileInputStream(sourceFile);
		OutputStream out = new FileOutputStream(moveArquivo);
		
		//Buffer para transportar os dados para o outro arquivo
		byte[] buf = new byte[1024];
		int lengh;
		
		while((lengh = in.read(buf)) > 0) {
			//Escreve os mesmos dados no novo arquivo
			out.write(buf, 0, lengh);
		}
		
		in.close();
		out.close();
	}
}
