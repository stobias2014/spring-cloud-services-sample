package com.tobias.saul.catalogservice.pojos;

import org.springframework.stereotype.Component;

@Component
public class ProductInventoryResponse {
	
	private String prouctCode;
	private int availableQuantity;
	
	public ProductInventoryResponse() {}

	public String getProuctCode() {
		return prouctCode;
	}

	public void setProuctCode(String prouctCode) {
		this.prouctCode = prouctCode;
	}

	public int getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + availableQuantity;
		result = prime * result + ((prouctCode == null) ? 0 : prouctCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductInventoryResponse other = (ProductInventoryResponse) obj;
		if (availableQuantity != other.availableQuantity)
			return false;
		if (prouctCode == null) {
			if (other.prouctCode != null)
				return false;
		} else if (!prouctCode.equals(other.prouctCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProductInventoryResponse [prouctCode=" + prouctCode + ", availableQuantity=" + availableQuantity + "]";
	}
	
	

}
