package com.icono.organicanimal;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by alfo6-10 on 2018-03-21.
 */

public class a_Fragment1_Item implements Parcelable, Serializable {
    String age;
    String careAddr;
    String careNm;
    String colorCd;
    String desertionNo;
    String filename;
    String happenDt;
    String happenPlace;
    String kindCd;
    String neuterYn;
    String noticeEdt;
    String noticeNo;
    String noticeSdt;
    String orgNm;
    String popfile;
    String processState;
    String sexCd;
    String specialMark;
    String weight;

    public a_Fragment1_Item() {
    }

    public a_Fragment1_Item(String age, String careAddr, String careNm, String colorCd, String desertionNo, String filename, String happenDt, String happenPlace, String kindCd, String neuterYn, String noticeEdt, String noticeNo, String noticeSdt, String orgNm, String popfile, String processState, String sexCd, String specialMark, String weight) {
        this.age = age;
        this.careAddr = careAddr;
        this.careNm = careNm;
        this.colorCd = colorCd;
        this.desertionNo = desertionNo;
        this.filename = filename;
        this.happenDt = happenDt;
        this.happenPlace = happenPlace;
        this.kindCd = kindCd;
        this.neuterYn = neuterYn;
        this.noticeEdt = noticeEdt;
        this.noticeNo = noticeNo;
        this.noticeSdt = noticeSdt;
        this.orgNm = orgNm;
        this.popfile = popfile;
        this.processState = processState;
        this.sexCd = sexCd;
        this.specialMark = specialMark;
        this.weight = weight;
    }

    public String getAge() {
        return age;
    }

    public String getCareAddr() {
        return careAddr;
    }

    public String getCareNm() {
        return careNm;
    }

    public String getColorCd() {
        return colorCd;
    }

    public String getDesertionNo() {
        return desertionNo;
    }

    public String getFilename() {
        return filename;
    }

    public String getHappenDt() {
        return happenDt;
    }

    public String getHappenPlace() {
        return happenPlace;
    }

    public String getKindCd() {
        return kindCd;
    }

    public String getNeuterYn() {
        return neuterYn;
    }

    public String getNoticeEdt() {
        return noticeEdt;
    }

    public String getNoticeNo() {
        return noticeNo;
    }

    public String getNoticeSdt() {
        return noticeSdt;
    }

    public String getOrgNm() {
        return orgNm;
    }

    public String getPopfile() {
        return popfile;
    }

    public String getProcessState() {
        return processState;
    }

    public String getSexCd() {
        return sexCd;
    }

    public String getSpecialMark() {
        return specialMark;
    }

    public String getWeight() {
        return weight;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setCareAddr(String careAddr) {
        this.careAddr = careAddr;
    }

    public void setCareNm(String careNm) {
        this.careNm = careNm;
    }

    public void setColorCd(String colorCd) {
        this.colorCd = colorCd;
    }

    public void setDesertionNo(String desertionNo) {
        this.desertionNo = desertionNo;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setHappenDt(String happenDt) {
        this.happenDt = happenDt;
    }

    public void setHappenPlace(String happenPlace) {
        this.happenPlace = happenPlace;
    }

    public void setKindCd(String kindCd) {
        this.kindCd = kindCd;
    }

    public void setNeuterYn(String neuterYn) {
        this.neuterYn = neuterYn;
    }

    public void setNoticeEdt(String noticeEdt) {
        this.noticeEdt = noticeEdt;
    }

    public void setNoticeNo(String noticeNo) {
        this.noticeNo = noticeNo;
    }

    public void setNoticeSdt(String noticeSdt) {
        this.noticeSdt = noticeSdt;
    }

    public void setOrgNm(String orgNm) {
        this.orgNm = orgNm;
    }

    public void setPopfile(String popfile) {
        this.popfile = popfile;
    }

    public void setProcessState(String processState) {
        this.processState = processState;
    }

    public void setSexCd(String sexCd) {
        this.sexCd = sexCd;
    }

    public void setSpecialMark(String specialMark) {
        this.specialMark = specialMark;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public a_Fragment1_Item(Parcel in) {
        age = in.readString();
        careAddr = in.readString();
        careNm = in.readString();
        colorCd = in.readString();
        desertionNo = in.readString();
        filename = in.readString();
        happenDt = in.readString();
        happenPlace = in.readString();
        kindCd = in.readString();
        neuterYn = in.readString();
        noticeEdt = in.readString();
        noticeNo = in.readString();
        noticeSdt = in.readString();
        orgNm = in.readString();
        popfile = in.readString();
        processState = in.readString();
        sexCd = in.readString();
        specialMark = in.readString();
        weight = in.readString();
    }

    public static final Creator<a_Fragment1_Item> CREATOR = new Creator<a_Fragment1_Item>() {
        @Override
        public a_Fragment1_Item createFromParcel(Parcel parcel) {
            return new a_Fragment1_Item(parcel);
        }

        @Override
        public a_Fragment1_Item[] newArray(int i) {
            return new a_Fragment1_Item[i];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(age);
        parcel.writeString(careAddr);
        parcel.writeString(careNm);
        parcel.writeString(colorCd);
        parcel.writeString(desertionNo);
        parcel.writeString(filename);
        parcel.writeString(happenDt);
        parcel.writeString(happenPlace);
        parcel.writeString(kindCd);
        parcel.writeString(neuterYn);
        parcel.writeString(noticeEdt);
        parcel.writeString(noticeNo);
        parcel.writeString(noticeSdt);
        parcel.writeString(orgNm);
        parcel.writeString(popfile);
        parcel.writeString(processState);
        parcel.writeString(sexCd);
        parcel.writeString(specialMark);
        parcel.writeString(weight);
    }
}