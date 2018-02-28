package com.test;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class FileProcServiceImpl implements FileProcService {

    String sessionId;
    String home_pth=System.getProperty("user.dir")+"/out";

    @Override
    public boolean FileUpload(HttpServletRequest request) {
        sessionId = request.getSession().getId();
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // Максимальный буфера данных в байтах,
        // при его привышении данные начнут записываться на диск во временную директорию
        // устанавливаем один мегабайт
        factory.setSizeThreshold(102400*1024);

        // устанавливаем временную директорию
        File tempDir =  new File("home_pth");
        factory.setRepository(tempDir);

        //Создаём сам загрузчик
        ServletFileUpload upload = new ServletFileUpload(factory);

        //максимальный размер данных который разрешено загружать в байтах
        //по умолчанию -1, без ограничений. Устанавливаем 100 мегабайт.
        //    upload.setSizeMax(1024 * 1024 * 100);
        try {
            List items = upload.parseRequest(request);
            Iterator iter = items.iterator();
            while (iter.hasNext())
            {
                FileItem item = (FileItem) iter.next();
                if (!item.isFormField())
                   if(processUploadedFile(item, sessionId ))
                       return true;
                    else
                        return false;
            }
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
        return false;
    }

    @Override
    public boolean FileProcess() {

        ArrayList<String> invArr=new ArrayList<String>();
        File folder = new File(home_pth+"//"+ sessionId);
        String Flist[] = folder.list();

        if(Flist==null)
            return false;
        for(String s:Flist) {
            // проверяем действительно ли PDF
            int ExtIndex = s.indexOf('.');
            if(s.substring(ExtIndex).toLowerCase().equals(".pdf")) {
                System.out.println("File to process:" + s);
                // Обрабатываем файл и получаем номер счета
                String invStr = ProccessPDFfile(folder.toString() + "//" + s);
                // запоминаем какие счета мы обработали
                if(!invStr.equals(""))invArr.add(invStr);
            }
        }
        System.out.println("Processed invoices:"+ String.join(", ", invArr));

        return true;
    }
    //--------------------------------------------------------------------------------------------------------------

    /**
     * Сохраняет файл на сервере, в папке upload.
     * Сама папка должна быть уже создана.
     *
     * @param item
     * @throws Exception
     */
    private Boolean processUploadedFile(FileItem item, String sessionId) throws Exception {

        String Filename =item.getName();
        File uploadetFile = new File(home_pth+"//"+sessionId+"//"+ Filename);
        //выбираем файлу имя пока не найдём свободное

        if(!uploadetFile.exists()&&Filename!="") {
            int ExtIndex = Filename.indexOf('.');
            if(Filename.substring(ExtIndex).toLowerCase().equals(".pdf")) {

                File folder = new File(home_pth+"//" + sessionId);
                if (!folder.exists())
                    folder.mkdirs();

                //создаём файл
                uploadetFile.createNewFile();
                //записываем в него данные
                item.write(uploadetFile);
                return true;
            }

        }
        return false;
    }
    //-------------------------------------------------
    public String GetFlist()
    {
        File folder = new File(home_pth+"//"+ sessionId);
        String str=Arrays.toString(folder.list());
        if(str=="null")return "";
        else
        return str;
    }
}
