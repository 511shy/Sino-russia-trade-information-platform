package com.highcom.admin.service;

import java.util.List;
import java.util.Map;

import com.highcom.admin.pojo.Order;
import com.highcom.admin.pojo.TotalInfo;

/**
 * ���ܱ���
 */
public interface  TotalService {
	/**
	 * ������Ϣ���� (��������  ����ɶ���  δ��ɶ��� �ɹ����� ��Ӧ����)
	 * @return ���ܺ��map����
	 */
	Map <String,Integer>totalOrder();

	/**
	 * ����ͳ�ƶ�������(��������ͼ)
	 * @param year ���
	 * @param supplyflag ��������( 0�ɹ� 1��Ӧ)
	 * @param tradeflag ���״̬( 0δ��� 1�����)
	 */
	List<TotalInfo> totalOrderByYear(Integer year,String supplyflag,String tradeflag);

	/**
	 * ��ҵ��Ϣͳ�� ��ҵ����, vip��ҵ��,��ͨ��ҵ��
	 * @return ���ܺ��map����
	 */
	Map<String, Integer> totalCompany();

	/**
	 * ��ҵ��Ϣͳ��,�õ�ĳ����ĳ��������ҵ����
	 * @param year ���
	 * @param month �·�
	 * @return ��������µ���ҵ����
	 */
	public int getCompanyCountByYM(Integer year,Integer month) ;
	/**
	 * ��ҵ��Ϣͳ��,�õ�ĳ����ĳ������vip��ҵ����
	 * @param year ���
	 * @param month �·�
	 * @return ��������µ�vip��ҵ����
	 */
	public int getVipCompanyCountByYM(Integer year,Integer month) ;

	/**
	 * ����������Ϣͳ��,�ܵ����,���󶩵������,�ɹ����������
	 * @return ���ܺ��map����
	 */
	Map<String, Integer> totalOrderClick();

	/**
	 * �õ������ǰn��ĳ�ඩ��
	 * @param n ��ѯ�����ٸ�
	 * @param supplyflag ��������( 0�ɹ� 1��Ӧ)
	 * @return ǰn��������Ϣ
	 */
	List<Order> getTopClick(Integer n, String supplyflag);

	/**
	 * �õ�������������Щ���
	 * @return ����б�
	 */
	List<String> getOrderYears();

	/**
	 * �õ���˾ע�ᶼ������Щ���
	 * @return ����б�
	 */
	List<String> getCompanyYears();
}
