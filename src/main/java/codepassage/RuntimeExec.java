package codepassage;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class RuntimeExec {
    public static void main(String[] args) throws IOException {
        String[] cmd = {"/bin/sh", "-c", "su"};
        Process ps = Runtime.getRuntime().exec(cmd);
        DataOutputStream os = new DataOutputStream(ps.getOutputStream());
        os.writeBytes("lxn\n");
        os.flush();
        BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }
}
