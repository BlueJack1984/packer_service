package com.packer.commons.sms.serialize;


import com.packer.commons.sms.jce.JceBase.DigestAlg;
import com.packer.commons.sms.lang.ByteUtil;
import com.packer.commons.sms.lang.EncodeUtil;
import com.packer.commons.sms.FileXMLSmsFactory;
import com.packer.commons.sms.mapping.impl.ExtentionType;
import com.packer.commons.sms.mapping.impl.StructType;
import com.packer.commons.sms.packet.IChannelManager;
import com.packer.commons.sms.packet.PacketChannel;
import com.packer.commons.sms.packet.PacketGroup;
import com.packer.commons.sms.packet.StructureUnit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Serializer;
import org.apache.log4j.Logger;

public class XMLSerializer {
    private Logger log = Logger.getLogger(XMLSerializer.class);
    private File dir;
    private String filename;

    public void delete(File dir) {
        File[] files = dir.listFiles();
        File[] var6 = files;
        int var5 = files.length;

        for(int var4 = 0; var4 < var5; ++var4) {
            File f = var6[var4];
            if (f.isFile()) {
                f.delete();
            } else {
                this.delete(f);
            }
        }

    }

    public XMLSerializer(String path) {
        File main = new File(path);
        this.dir = main.getParentFile();
        this.filename = main.getName();
        if (!this.dir.exists()) {
            this.dir.mkdirs();
        }

    }

    public void write(List<PacketChannel> channels, Set<String> oldFiles) {
        for(int i = 0; i < channels.size(); ++i) {
            PacketChannel channel = (PacketChannel)channels.get(i);
            channel.validate();
        }

        if (!this.dir.isDirectory()) {
            throw new SerializerException("the file path must be a directory");
        } else {
            this.channelPersist(this.dir.getAbsolutePath(), channels);
        }
    }

    private void write(String xml, String filepath, String filename) {
        String dir = filepath + File.separator;
        String targetFile = dir + filename;
        String tmpFile = targetFile + ".tmp";

        try {
            File saveDir = new File(dir);
            if (!saveDir.exists()) {
                saveDir.mkdirs();
            }

            File targetOut = new File(targetFile);
            File tmpOut = new File(tmpFile);
            if (targetOut.exists()) {
                byte[] oldmd5 = EncodeUtil.digestFile(targetOut, DigestAlg.MD5);
                this.serialXml(xml, tmpOut);
                byte[] newmd5 = EncodeUtil.digestFile(new File(filepath + File.separator + filename + ".tmp"), DigestAlg.MD5);
                String ol = ByteUtil.bytes2HEX(oldmd5);
                String ne = ByteUtil.bytes2HEX(newmd5);
                if (!ol.equals(ne)) {
                    targetOut.delete();
                } else {
                    tmpOut.delete();
                }

                tmpOut.renameTo(targetOut);
            } else {
                this.serialXml(xml, targetOut);
            }

        } catch (Exception var14) {
            throw new SerializerException("failed to write [" + xml + "] to the file:" + filename, var14);
        }
    }

    private void serialXml(String xml, File file) {
        try {
            Builder b = new Builder();
            Document doc = b.build(new StringReader(xml));
            FileOutputStream fos = new FileOutputStream(file);
            Serializer s = new Serializer(fos, "utf-8");
            s.setIndent(4);
            s.write(doc);
            fos.close();
        } catch (Exception var7) {
            throw new SerializerException("failed to write [" + xml + "] to the file:" + file.getAbsolutePath(), var7);
        }
    }

    private void channelPersist(String filepath, List<PacketChannel> channels) {
        HashMap hm = new HashMap();
        hm.put("channels", channels);
        String channel = TemplateUtil.getBody(hm, "Channel.ftl");
        this.write(channel, filepath, this.filename);
        Iterator var6 = channels.iterator();

        while(var6.hasNext()) {
            PacketChannel pc = (PacketChannel)var6.next();

            int i;
            StructureUnit mt;
            for(i = 0; i < pc.getMoUnits().size(); ++i) {
                mt = (StructureUnit)pc.getMoUnits().get(i);
                this.structPersist(mt, filepath + File.separator + pc.getName() + File.separator);
            }

            for(i = 0; i < pc.getMtUnits().size(); ++i) {
                mt = (StructureUnit)pc.getMtUnits().get(i);
                this.structPersist(mt, filepath + File.separator + pc.getName() + File.separator);
            }
        }

    }

    private void structPersist(StructureUnit su, String filepath) {
        HashMap hm = new HashMap();
        hm.put("struct", su);
        String struct = TemplateUtil.getBody(hm, "Struct.ftl");
        this.write(struct, filepath, su.getName() + ".xml");
        Iterator var6 = su.getPacketGroups().iterator();

        while(var6.hasNext()) {
            PacketGroup pg = (PacketGroup)var6.next();
            this.packetGroupPersist(pg, filepath + File.separator + su.getName());
        }

    }

    private void packetGroupPersist(PacketGroup pg, String filepath) {
        HashMap hm = new HashMap();
        hm.put("packetgroup", pg);
        String packet = TemplateUtil.getBody(hm, "Packet.ftl");
        this.write(packet, filepath, pg.getName() + ".xml");
    }

    public File getDir() {
        return this.dir;
    }

    public void setDir(File dir) {
        this.dir = dir;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public static void main(String[] args) {
        FileXMLSmsFactory f = new FileXMLSmsFactory();
        //f.getCondition().addUnload(StructType.MO, ExtentionType.ValueSource);
        f.setConfig("C:/packer/wdpacker-cfg.xml");
        IChannelManager cm = f.getManager();
        PacketChannel pc = cm.loadPacketChannel("sms");
        List<PacketChannel> list = new ArrayList();
        list.add(pc);
        FileXMLSmsFactory ff = new FileXMLSmsFactory();
        ff.setConfig("c:/sms1/packet-config.xml");
        ff.getManager().savePacketChannels(list);
    }
}
