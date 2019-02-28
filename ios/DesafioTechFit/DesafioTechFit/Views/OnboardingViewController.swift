//
//  OnboardingViewController.swift
//  DesafioTechFit
//
//  Created by Bruno Garcia on 24/02/19.
//  Copyright © 2019 Bruno Garcia. All rights reserved.
//

import UIKit
import paper_onboarding

class OnboardingViewController: UIViewController {

    // MARK: - Outlets
    @IBOutlet weak var OnboardingObj: OnboardingViewClass!
    @IBOutlet weak var btnLetsGo: UIButton!
    
    //MARK: - Properties
    let titleFont = UIFont(name: "HelveticaNeue-Bold", size: 18)!
    let descFont = UIFont(name: "HelveticaNeue", size: 14)!
    
    let bgOne = #colorLiteral(red: 0.1099999994, green: 0.5249999762, blue: 0.9330000281, alpha: 1)
    let bgTwo = #colorLiteral(red: 0.7450980544, green: 0.1568627506, blue: 0.07450980693, alpha: 1)
    let bgThree = #colorLiteral(red: 0.1960784346, green: 0.3411764801, blue: 0.1019607857, alpha: 1)
    let textColor = #colorLiteral(red: 1.0, green: 1.0, blue: 1.0, alpha: 1.0)
    
    
    // MARK: - Main Methods
    override func viewDidLoad() {
        super.viewDidLoad()
        UserDefaultsManager.setOnboardingShown(false)
        OnboardingObj.dataSource = self
        OnboardingObj.delegate = self
        
        btnLetsGo.titleLabel!.font = UIFont(name: "HelveticaNeue-Bold", size: 24)!
        btnLetsGo.titleLabel!.textColor = textColor
        
        btnLetsGo.layer.cornerRadius = 5
        
    }
    
    //MARK: - IBActions
    @IBAction func letsGoClick(_ sender: Any) {
        UserDefaultsManager.setOnboardingShown(true)
    }
    

}

extension OnboardingViewController: PaperOnboardingDelegate {
    func onboardingConfigurationItem(_ item: OnboardingContentViewItem, index _: Int) {
        
    }
    
    func onboardingWillTransitonToIndex(_ index: Int) {
        if index == 1 {
            if self.btnLetsGo.alpha == 1 {
                UIView.animate(withDuration: 0.2, animations: {
                    self.btnLetsGo.alpha = 0
                })
            }
            
        }
    }
    
    func onboardingDidTransitonToIndex(_ index: Int) {
        if index == 2 {
            UIView.animate(withDuration: 0.4, animations: {
                self.btnLetsGo.alpha = 1
            })
        }
    }
}

extension OnboardingViewController: PaperOnboardingDataSource {
    func onboardingItemsCount() -> Int {
        return 3
    }
    
    func onboardingItem(at index: Int) -> OnboardingItemInfo {
        
        
        
        return [
            OnboardingItemInfo(informationImage: UIImage(named: "cat_init")! ,
                               title: "Seja Bem Vindo!!",
                               description: "Ao melhor APP voltado para amantes de gatos!!",
                               pageIcon:  UIImage(named: "cat_tab")!,
                               color: bgOne,
                               titleColor: textColor,
                               descriptionColor: textColor,
                               titleFont: titleFont,
                               descriptionFont: descFont),
            
            OnboardingItemInfo(informationImage: UIImage(named: "img_list_01")!,
                               title: "😸",
                               description: "Divirta-se com diversas imagens de lindos gatinhos!!",
                               pageIcon: UIImage(named: "cat_tab")! ,
                               color: bgTwo,
                               titleColor: textColor,
                               descriptionColor: textColor,
                               titleFont: titleFont,
                               descriptionFont: descFont),
            
            OnboardingItemInfo(informationImage: UIImage(named: "img_list_02")!,
                               title: "😻",
                               description: "E não economize nos Likes!!",
                               pageIcon: UIImage(named: "cat_tab")! ,
                               color: bgThree,
                               titleColor: textColor,
                               descriptionColor: textColor,
                               titleFont: titleFont,
                               descriptionFont: descFont)
            
            
                ][index]
        
        
    }
    
    
    
    
}
