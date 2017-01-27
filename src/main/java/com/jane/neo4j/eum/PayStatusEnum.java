package com.jane.neo4j.eum;

public enum PayStatusEnum {
	
	    //已创建 
		//待支付 支付成功/支付失败        发布中 
	                               // 审核中 审核成功/审核失败  发布
															//传播中 传播结束
		
	
	待支付("待支付", 0), 支付成功("支付成功", 1), 支付失败("支付失败", 2);
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private PayStatusEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (PayStatusEnum c : PayStatusEnum.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
    
    

}
