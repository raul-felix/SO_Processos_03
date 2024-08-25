package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DistroController {

	public DistroController() {
		super();
	}
	
	private String os() {
		return System.getProperty("os.name");
	}
	
	public void exibeDistro() {
		String osName = os().toLowerCase();
		if (osName.contains("linux")) {
			readProcess("cat /etc/os-release");
		} else {
			System.out.println("Sistema operacional inválido");
		}
	}
	
	public void callProcess(String proc) {
		try {
			Runtime.getRuntime().exec(proc.split(" "));
		} catch (Exception e) {
			String msg = e.getMessage();
			if (msg.contains("740")) {
				StringBuffer buffer = new StringBuffer();
				buffer.append("cmd /c ");
				buffer.append(proc);
				try {
					Runtime.getRuntime().exec(buffer.toString().split(" "));					
				}catch (Exception e1) {
					System.err.println(e1.getMessage());
				}
			} else {
				System.err.println(msg);
			}
		}
	}
	
	public void readProcess(String proc) {
		try {
			Process p = Runtime.getRuntime().exec(proc.split(" "));
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while (linha != null) {
				System.out.println(linha);
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
