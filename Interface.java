package TubesPBO;

import java.sql.*;
 
//interface
public interface Interface {
    public void Kasir()  throws SQLException;
    public void NoNota();
    public void NamaPembeli();
    public void KodeBolu();
    public void NamaBolu();
    public void HargaBolu();
    public void Jumlah();
    public void SubTotal();
    public void Diskon();
    public void TotalHarga();
}
