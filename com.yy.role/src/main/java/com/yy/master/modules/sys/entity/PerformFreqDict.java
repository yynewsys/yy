/**
 * jims
 */
package com.yy.master.modules.sys.entity;

import com.yy.master.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 频次字典表Entity
 * @author 刘方舟
 * @version 2017-03-15
 */
public class PerformFreqDict extends DataEntity<PerformFreqDict> {
	
	private static final long serialVersionUID = 1L;
	private String freqDesc;		// 执行频率描述
	private String freqCounter;		// 频率次数
	private String freqInterval;		// 频次间隔
	private String freqIntervalUnit;		// 频次单位
    private String performTime;

	public PerformFreqDict() {
		super();
	}

	public PerformFreqDict(String id){
		super(id);
	}

	@Length(min=1, max=20, message="执行频率描述长度必须介于 1 和 20 之间")
	public String getFreqDesc() {
		return freqDesc;
	}

	public void setFreqDesc(String freqDesc) {
		this.freqDesc = freqDesc;
	}
	
	public String getFreqCounter() {
		return freqCounter;
	}

	public void setFreqCounter(String freqCounter) {
		this.freqCounter = freqCounter;
	}
	
	public String getFreqInterval() {
		return freqInterval;
	}

	public void setFreqInterval(String freqInterval) {
		this.freqInterval = freqInterval;
	}
	
	@Length(min=0, max=20, message="频次单位长度必须介于 0 和 20 之间")
	public String getFreqIntervalUnit() {
		return freqIntervalUnit;
	}

	public void setFreqIntervalUnit(String freqIntervalUnit) {
		this.freqIntervalUnit = freqIntervalUnit;
	}

    public String getPerformTime() {
        return performTime;
    }

    public void setPerformTime(String performTime) {
        this.performTime = performTime;
    }
}