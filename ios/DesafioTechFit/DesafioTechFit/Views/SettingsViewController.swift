//
//  SettingsViewController.swift
//  DesafioTechFit
//
//  Created by Bruno Garcia on 28/02/19.
//  Copyright © 2019 Bruno Garcia. All rights reserved.
//

import UIKit

class SettingsViewController: UIViewController {

    //MARK: - IBOutlets
    @IBOutlet weak var scTheme: UISegmentedControl!
    
    //MARK: - Main Methods
    override func viewDidLoad() {
        super.viewDidLoad()
        NotificationCenter.default.addObserver(self, selector: #selector(applicationDidBecomeActive), name: UIApplication.didBecomeActiveNotification, object: nil)
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        setTheme()
    }
    
    // MARK: - Methods
    @objc func applicationDidBecomeActive(notification: NSNotification) {
        setTheme()
        scTheme.selectedSegmentIndex = UserDefaultsManager.themeNumber()
    }
    
    func setTheme() {
        self.navigationController?.navigationBar.prefersLargeTitles = true
        self.navigationController?.navigationBar.barTintColor = UIColor(named: ThemeColorsBackground.allValues[UserDefaultsManager.themeNumber()].rawValue)
        self.view.backgroundColor = UIColor(named: ThemeColorsBackground.allValues[UserDefaultsManager.themeNumber()].rawValue)
        
        self.navigationController?.navigationBar.titleTextAttributes = [NSAttributedString.Key.foregroundColor: UIColor.white]
        self.navigationController?.navigationBar.largeTitleTextAttributes = [NSAttributedString.Key.foregroundColor: UIColor.white]
    }
    
    // MARK: - IBActions
    @IBAction func changeTheme(_ sender: UISegmentedControl) {
        UserDefaultsManager.setTheme(to: sender.selectedSegmentIndex)
        setTheme()
    }
    

}
