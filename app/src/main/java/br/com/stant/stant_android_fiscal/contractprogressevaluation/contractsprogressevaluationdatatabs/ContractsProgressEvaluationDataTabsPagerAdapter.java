package br.com.stant.stant_android_fiscal.contractprogressevaluation.contractsprogressevaluationdatatabs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import br.com.stant.stant_android_fiscal.InjectionUseCase;
import br.com.stant.stant_android_fiscal.contractprogressevaluation.constractsprogressevaluation.ContractsProgressEvaluationFragment;
import br.com.stant.stant_android_fiscal.contractprogressevaluation.constractsprogressevaluation.ContractsProgressEvaluationPresenter;
import br.com.stant.stant_android_fiscal.domain.enums.contractprogressevaluation.ContractProgressEvaluationStatusEnum;

/**
 * Created by denisvieira on 11/05/17.
 */

public class ContractsProgressEvaluationDataTabsPagerAdapter extends FragmentPagerAdapter {

    private String[] mTabTitles;
    private int mConstructionSiteId;

    public ContractsProgressEvaluationDataTabsPagerAdapter(FragmentManager fm, String[] mTabTitles, int constructionSiteId) {
        super(fm);
        this.mTabTitles = mTabTitles;
        this.mConstructionSiteId = constructionSiteId;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ContractsProgressEvaluationFragment contractsProgressEvaluationProgressFragment = ContractsProgressEvaluationFragment.newInstance(mConstructionSiteId, ContractProgressEvaluationStatusEnum.PROGRESS);
                new ContractsProgressEvaluationPresenter(contractsProgressEvaluationProgressFragment, InjectionUseCase.provideGetContractsProgressEvaluation(contractsProgressEvaluationProgressFragment.getContext()));
                return contractsProgressEvaluationProgressFragment;
            case 1:
                ContractsProgressEvaluationFragment contractsProgressEvaluationFinalizedFragment = ContractsProgressEvaluationFragment.newInstance(mConstructionSiteId, ContractProgressEvaluationStatusEnum.FINISH);
                new ContractsProgressEvaluationPresenter(contractsProgressEvaluationFinalizedFragment, InjectionUseCase.provideGetContractsProgressEvaluation(contractsProgressEvaluationFinalizedFragment.getContext()));
                return contractsProgressEvaluationFinalizedFragment;
            case 2:
                ContractsProgressEvaluationFragment contractsProgressEvaluationApprovedFragment = ContractsProgressEvaluationFragment.newInstance(mConstructionSiteId, ContractProgressEvaluationStatusEnum.APPROVED);
                new ContractsProgressEvaluationPresenter(contractsProgressEvaluationApprovedFragment, InjectionUseCase.provideGetContractsProgressEvaluation(contractsProgressEvaluationApprovedFragment.getContext()));
                return contractsProgressEvaluationApprovedFragment;
            default:
                return null;
        }
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return this.mTabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return this.mTabTitles[position];
    }
}
